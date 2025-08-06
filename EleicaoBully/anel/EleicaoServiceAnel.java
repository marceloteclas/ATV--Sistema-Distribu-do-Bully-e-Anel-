package EleicaoBully.anel;

import java.util.List;

/**
 * Classe responsável por implementar o algoritmo de eleição Bully.
 * Gerencia o início da eleição e a notificação do novo coordenador.
 */
public class EleicaoServiceAnel {

    /**
     * Inicia o algoritmo de eleição Bully.
     * O processo solicitante envia mensagens para todos com ID maior e espera resposta.
     * Se não houver resposta, ele se torna o novo coordenador.
     * @param solicitante Processo que detectou a falha e iniciou a eleição
     * @param todosProcessos Lista de todos os processos do sistema
     */
    public void iniciarEleicao(ProcessoAnel solicitante, List<ProcessoAnel> todosProcessos) {
        System.out.println("P" + solicitante.getIdProcesso() + " iniciou eleição...");

        boolean houveResposta = false;

        for (ProcessoAnel p : todosProcessos) {
            if (p.getIdProcesso() > solicitante.getIdProcesso() && p.isAtivo()) {
                System.out.println("P" + solicitante.getIdProcesso() + " envia ELEICAO para P" + p.getIdProcesso());
                houveResposta = true;
            }
        }

        // Se não houve resposta de nenhum processo com ID maior, solicitante vira coordenador
        if (!houveResposta) {
            solicitante.setCoordenador(true);
            System.out.println("P" + solicitante.getIdProcesso() + " se tornou o novo COORDENADOR");
            notificarCoordenador(solicitante, todosProcessos);
        }
    }

    /**
     * Notifica todos os processos ativos sobre o novo coordenador eleito.
     * @param coordenador Processo eleito como novo coordenador
     * @param todosProcessos Lista de todos os processos do sistema
     */
    public void notificarCoordenador(ProcessoAnel coordenador, List<ProcessoAnel> todosProcessos) {
        for (ProcessoAnel p : todosProcessos) {
            if (p.getIdProcesso() != coordenador.getIdProcesso() && p.isAtivo()) {
                p.receberCoordenador(coordenador.getIdProcesso());
            }
        }
    }
}