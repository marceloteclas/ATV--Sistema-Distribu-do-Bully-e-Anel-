package EleicaoBully;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import EleicaoBully.anel.AlgoritmoAnel;
import EleicaoBully.bully.EleicaoServiceBully;
import EleicaoBully.bully.ProcessoBully;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o algoritmo de eleição:");
        System.out.println("1 - Algoritmo Bully");
        System.out.println("2 - Algoritmo Anel");
        System.out.print("Digite a opção (1 ou 2): ");
        int opcao = scanner.nextInt();
        scanner.close();

        if (opcao == 1) {
            executarBully();
        } else if (opcao == 2) {
            executarAnel();
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void executarBully() throws InterruptedException {
        EleicaoServiceBully eleicaoService = new EleicaoServiceBully();
        List<ProcessoBully> processos = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            processos.add(new ProcessoBully(i, processos, eleicaoService));
        }

        for (ProcessoBully p : processos) {
            p.start();
        }

        Thread.sleep(2000);
        System.out.println("\n--- Iniciando eleição com P5 ---\n");
        processos.get(4).iniciarEleicao();

        Thread.sleep(8000);
        System.out.println("\n--- CENÁRIO A: Falha no coordenador (P5) ---\n");
        processos.get(4).desativar();

        Thread.sleep(8000);
        System.out.println("\n--- CENÁRIO A: P5 retorna ao sistema ---\n");
        processos.get(4).ativar();
        processos.get(4).iniciarEleicao();

        Thread.sleep(10000);
        System.out.println("\n--- CENÁRIO B: P5 e P4 falham simultaneamente ---\n");
        processos.get(3).desativar();
        processos.get(4).desativar();

        Thread.sleep(8000);
        System.out.println("\n--- FIM DA SIMULAÇÃO ---");
        for (ProcessoBully p : processos) {
    p.encerrar();
}

    }

    private static void executarAnel() throws InterruptedException {
        Monitor monitor = new Monitor(5);
        monitor.iniciar();

        Thread.sleep(2000);

        System.out.println("\n--- CENÁRIO A: Falha no coordenador (Anel) ---\n");
        monitor.falharProcesso(monitor.getCoordenador());

        Thread.sleep(8000);

        System.out.println("\n--- CENÁRIO A: Coordenador retorna ao sistema ---\n");
        monitor.recuperarProcesso(4);
        new AlgoritmoAnel(monitor).iniciarEleicao();

        Thread.sleep(10000);

        System.out.println("\n--- CENÁRIO B: Múltiplos processos falham ---\n");
        monitor.falharProcesso(3);
        monitor.falharProcesso(4);

        Thread.sleep(8000);
        System.out.println("\n--- FIM DA SIMULAÇÃO ---");
    }
}
