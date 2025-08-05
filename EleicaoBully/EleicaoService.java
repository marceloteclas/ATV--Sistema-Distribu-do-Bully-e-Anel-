package EleicaoBully;

import EleicaoBully.Processo;
import java.util.List;

public class EleicaoService {

    public void iniciarEleicao(Processo solicitante, List<Processo> todosProcessos) {
        System.out.println("P" + solicitante.getIdProcesso() + " iniciou eleição...");

        boolean houveResposta = false;

        for (Processo p : todosProcessos) {
            if (p.getIdProcesso() > solicitante.getIdProcesso() && p.isAtivo()) {
                System.out.println("P" + solicitante.getIdProcesso() + " envia ELEICAO para P" + p.getIdProcesso());
                houveResposta = true;
            }
        }

        if (!houveResposta) {
            solicitante.setCoordenador(true);
            System.out.println("P" + solicitante.getIdProcesso() + " se tornou o novo COORDENADOR");
            notificarCoordenador(solicitante, todosProcessos);
        }
    }

    public void notificarCoordenador(Processo coordenador, List<Processo> todosProcessos) {
        for (Processo p : todosProcessos) {
            if (p.getIdProcesso() != coordenador.getIdProcesso() && p.isAtivo()) {
                p.receberCoordenador(coordenador.getIdProcesso());
            }
        }
    }
}
