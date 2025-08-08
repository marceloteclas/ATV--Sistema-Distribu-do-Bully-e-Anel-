package EleicaoBullyAnel.bully;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classe que representa um processo participante do algoritmo Bully.
 * Cada processo possui um ID, pode estar ativo/inativo e pode ser eleito coordenador.
 */
public class ProcessoBully extends Thread {
    private final int id; // Identificador único do processo
    private final List<ProcessoBully> todosProcessos; // Lista de todos os processos Bully
    private final AtomicBoolean ativo = new AtomicBoolean(true); // Estado do processo
    private boolean coordenador = false; // Indica se é o coordenador atual
    private final EleicaoServiceBully eleicaoService; // Serviço de eleição Bully

    /**
     * Construtor do processo Bully.
     * @param id Identificador do processo
     * @param todosProcessos Lista de todos os processos Bully
     * @param eleicaoService Serviço de eleição Bully
     */
    public ProcessoBully(int id, List<ProcessoBully> todosProcessos, EleicaoServiceBully eleicaoService) {
        this.id = id;
        this.todosProcessos = todosProcessos;
        this.eleicaoService = eleicaoService;
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
        return ativo.get();
    }

    /**
     * Desativa o processo (simula falha).
     */
    public void desativar() {
        ativo.set(false);
        System.out.println("P" + id + " foi desativado.");
    }

    /**
     * Ativa o processo (simula recuperação).
     */
    public void ativar() {
        ativo.set(true);
        System.out.println("P" + id + " foi reativado.");
    }

    /**
     * Retorna se o processo é o coordenador.
     */
    public boolean isCoordenador() {
        return coordenador;
    }

    /**
     * Define se o processo é o coordenador.
     * @param coordenador true se for o novo coordenador
     */
    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
        if (coordenador) {
            System.out.println("P" + id + " foi eleito como COORDENADOR.");
        }
    }

    /**
     * Inicia o algoritmo de eleição Bully.
     */
    public void iniciarEleicao() {
        eleicaoService.iniciarEleicao(this, todosProcessos);
    }

    /**
     * Notifica o processo sobre o novo coordenador eleito.
     * @param novoCoord ID do novo coordenador
     */
    public void receberCoordenador(int novoCoord) {
        System.out.println("P" + id + " reconhece P" + novoCoord + " como COORDENADOR");
        this.coordenador = (this.id == novoCoord);
    }

    /**
     * Loop principal do processo, que verifica o coordenador periodicamente.
     */
    @Override
public void run() {
    try {
        while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(3000);

            if (!isAtivo()) {
                continue; // Se estiver inativo, não faz nada
            }

            ProcessoBully coord = encontrarCoordenador();

            if (isCoordenador()) {
                System.out.println("Coordenador (P" + id + ") está ativo.");
            } else if (coord == null) {
                System.out.println("P" + id + " detectou falha do coordenador!");
                iniciarEleicao();

                // Aguarda propagação da eleição antes de verificar de novo
                Thread.sleep(5000);
            }
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
public void encerrar() {
    this.interrupt(); // Interrompe o thread
}



    /**
     * Procura o coordenador ativo na lista de processos.
     * @return Processo coordenador ativo ou null se não houver
     */
    private ProcessoBully encontrarCoordenador() {
        for (ProcessoBully p : todosProcessos) {
            if (p.isCoordenador() && p.isAtivo()) {
                return p;
            }
        }
        return null;
    }
}