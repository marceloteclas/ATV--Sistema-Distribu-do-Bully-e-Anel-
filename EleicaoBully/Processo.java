package EleicaoBully;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Processo extends Thread {
    private final int id;
    private final List<Processo> todosProcessos;
    private final AtomicBoolean ativo = new AtomicBoolean(true);
    private boolean coordenador = false;
    private final EleicaoService eleicaoService;

    public Processo(int id, List<Processo> todosProcessos, EleicaoService eleicaoService) {
        this.id = id;
        this.todosProcessos = todosProcessos;
        this.eleicaoService = eleicaoService;
    }

    public int getIdProcesso() {
        return id;
    }

    public boolean isAtivo() {
        return ativo.get();
    }

    public void desativar() {
        ativo.set(false);
    }

    public void ativar() {
        ativo.set(true);
    }

    public boolean isCoordenador() {
        return coordenador;
    }

    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
    }

    public void iniciarEleicao() {
        eleicaoService.iniciarEleicao(this, todosProcessos);
    }

    public void receberCoordenador(int novoCoord) {
        System.out.println("P" + id + " reconhece P" + novoCoord + " como COORDENADOR");
        this.coordenador = false;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(3000);

                if (!ativo.get())
                    continue; // Processo inativo não faz nada

                if (coordenador) {
                    System.out.println("Coordenador (P" + id + ") está ativo.");
                } else {
                    Processo coord = encontrarCoordenador();
                    if (coord == null) {
                        System.out.println("P" + id + " detectou falha do coordenador!");
                        iniciarEleicao();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Processo encontrarCoordenador() {
        for (Processo p : todosProcessos) {
            if (p.isCoordenador() && p.isAtivo()) {
                return p;
            }
        }
        return null;
    }

}
