package EleicaoBully;
import java.util.*;

/**
 * Classe responsável por gerenciar os processos e o coordenador.
 * Permite simular falhas, recuperações e iniciar eleições.
 */
public class Monitor {
    private List<Processo> processos; // Lista de processos do sistema
    private int coordenador; // ID do coordenador atual

    /**
     * Construtor do monitor.
     * @param n Quantidade de processos
     */
    public Monitor(int n) {
        processos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            processos.add(new Processo(i, this));
        }
        coordenador = n - 1; // Inicialmente o de maior ID
    }

    /**
     * Inicia todos os processos e informa o coordenador inicial.
     */
    public void iniciar() {
        for (Processo p : processos) {
            p.start();
        }
        log("Coordenador inicial: " + coordenador);
    }

    /**
     * Marca o processo como inativo e inicia eleição se for o coordenador.
     * @param id ID do processo a ser desativado
     */
    public void falharProcesso(int id) {
        processos.get(id).desativar();
        if (id == coordenador) {
            log("Coordenador caiu. Iniciando eleição...");
            new AlgoritmoAnel(this).iniciarEleicao();
        }
    }

    /**
     * Marca o processo como ativo novamente.
     * @param id ID do processo a ser reativado
     */
    public void recuperarProcesso(int id) {
        processos.get(id).ativar();
    }

    /**
     * Retorna o processo pelo ID.
     */
    public Processo getProcesso(int id) {
        return processos.get(id);
    }

    /**
     * Retorna a lista de processos.
     */
    public List<Processo> getProcessos() {
        return processos;
    }

    /**
     * Retorna o ID do coordenador atual.
     */
    public int getCoordenador() {
        return coordenador;
    }

    /**
     * Define o novo coordenador.
     * @param novoCoord ID do novo coordenador
     */
    public void setCoordenador(int novoCoord) {
        coordenador = novoCoord;
        log("Novo coordenador eleito: " + coordenador);
    }

    /**
     * Exibe uma mensagem de log no console.
     * @param mensagem Mensagem a ser exibida
     */
    public void log(String mensagem) {
        System.out.println("[LOG] " + mensagem);
    }
}
