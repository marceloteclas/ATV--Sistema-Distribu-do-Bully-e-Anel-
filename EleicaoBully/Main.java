package EleicaoBully;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EleicaoService eleicaoService = new EleicaoService();
        List<Processo> processos = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            processos.add(new Processo(i, processos, eleicaoService));
        }

        // Iniciar todos os processos (threads)
        for (Processo p : processos) {
            p.start();
        }

        // Aguarda 2 segundos e define P5 como coordenador
        Thread.sleep(2000);
        processos.get(4).iniciarEleicao(); // P5

        // Aguarda 8 segundos e simula falha de P5
        Thread.sleep(8000);
        System.out.println("\n--- CENÁRIO A: Falha no coordenador (P5) ---\n");
        processos.get(4).desativar(); // P5 falha

        // Aguarda 8 segundos para permitir eleição automática
        Thread.sleep(8000);

        // CENÁRIO A: P5 retorna e tenta reassumir
        System.out.println("\n--- CENÁRIO A: P5 retorna ao sistema ---\n");
        processos.get(4).ativar(); // P5 volta
        processos.get(4).iniciarEleicao(); // P5 tenta reassumir

        // Aguarda 10 segundos antes do cenário B
        Thread.sleep(10000);

        // CENÁRIO B: Falha de múltiplos processos
        System.out.println("\n--- CENÁRIO B: P5 e P4 falham simultaneamente ---\n");
        processos.get(3).desativar(); // P4 falha
        processos.get(4).desativar(); // P5 falha de novo

        // Aguarda 8 segundos para permitir nova eleição entre P1, P2, P3
        Thread.sleep(8000);

        System.out.println("\n--- FIM DA SIMULAÇÃO ---\n");
    }
}
