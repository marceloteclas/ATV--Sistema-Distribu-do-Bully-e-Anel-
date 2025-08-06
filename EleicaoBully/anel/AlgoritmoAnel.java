package EleicaoBully.anel;

import EleicaoBully.Monitor;

/**
 * Classe que implementa o algoritmo de eleição por anel.
 * Responsável por coordenar a eleição de um novo coordenador quando necessário.
 */
public class AlgoritmoAnel {
    private Monitor monitor; // Referência ao monitor que gerencia os processos

    /**
     * Construtor do algoritmo de anel.
     * @param monitor Referência ao monitor do sistema
     */
    public AlgoritmoAnel(Monitor monitor) {
        this.monitor = monitor;
    }

    /**
     * Inicia o processo de eleição pelo algoritmo de anel.
     * Percorre os processos em ordem circular, coletando o maior ID ativo.
     * O processo com maior ID é eleito coordenador.
     */
    public void iniciarEleicao() {
        int n = monitor.getProcessos().size();
        int atual = (monitor.getCoordenador() + 1) % n; // Começa do próximo ao coordenador
        int idMaior = -1; // Armazena o maior ID encontrado

        while (true) {
            ProcessoAnel p = monitor.getProcesso(atual);
            if (p.isAtivo()) {
                monitor.log("Processo " + atual + " participa da eleição.");
                if (atual > idMaior) {
                    idMaior = atual;
                }
            }
            atual = (atual + 1) % n;
            // Quando der a volta completa, encerra a eleição
            if (atual == ((monitor.getCoordenador() + 1) % n)) break;
        }

        monitor.setCoordenador(idMaior); // Define o novo coordenador
    }
}