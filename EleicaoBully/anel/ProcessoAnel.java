package EleicaoBully.anel;

/**
 * Classe que representa um processo participante do sistema distribuído.
 * Cada processo possui um ID, pode estar ativo/inativo e pode ser eleito coordenador.
 * Os processos são gerenciados por um Monitor.
 */
public class ProcessoAnel extends Thread {
    private int id; // Identificador único do processo
    private boolean ativo; // Indica se o processo está ativo
    private boolean coordenador = false; // Indica se é o coordenador atual
    private Monitor monitor; // Referência ao monitor que gerencia os processos
    private boolean ultimoEstadoCoordenador = false; // Armazena o último estado do coordenador para evitar mensagens repetidas


    /**
     * Retorna se o processo é o coordenador.
     */
    public boolean isCoordenador() {
        return coordenador;
    }

    /**
     * Construtor do processo.
     * @param id Identificador do processo
     * @param monitor Referência ao monitor
     */
    public ProcessoAnel(int id, Monitor monitor) {
        this.id = id;
        this.monitor = monitor;
        this.ativo = true;
    }

    /**
     * Retorna o ID do processo.
     */
    public int getIdProcesso() {
        return id;
    }

    /**
     * Retorna se o processo está ativo.
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * Desativa o processo (simula falha).
     */
    public void desativar() {
        ativo = false;
        monitor.log("Processo " + id + " foi desativado.");
    }

    /**
     * Ativa o processo (simula recuperação).
     */
    public void ativar() {
        ativo = true;
        monitor.log("Processo " + id + " foi reativado.");
    }

    /**
     * Define se o processo é o coordenador.
     * @param coordenador true se for o novo coordenador
     */
    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
        if (coordenador) {
            monitor.log("Processo " + id + " foi eleito como COORDENADOR.");
        }
    }

    /**
     * Notifica o processo sobre o novo coordenador eleito.
     * @param idCoordenador ID do novo coordenador
     */
    public void receberCoordenador(int idCoordenador) {
        monitor.log("Processo " + id + " reconhece " + idCoordenador + " como novo coordenador.");
        this.coordenador = (this.id == idCoordenador);
    }

    /**
     * Loop principal do processo, que periodicamente informa se é o coordenador.
     */
    @Override
public void run() {
    while (true) {
        if (ativo && id == monitor.getCoordenador()) {
            if (!ultimoEstadoCoordenador) {
                monitor.log("Processo " + id + " é o coordenador atual.");
                ultimoEstadoCoordenador = true;
            }
        } else {
            ultimoEstadoCoordenador = false;
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            break;
        }
    }
}
}