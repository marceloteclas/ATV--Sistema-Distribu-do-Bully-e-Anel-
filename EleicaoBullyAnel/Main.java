package EleicaoBullyAnel;

import EleicaoBullyAnel.anel.AlgoritmoAnel;
import EleicaoBullyAnel.anel.Monitor;
import EleicaoBullyAnel.bully.EleicaoServiceBully;
import EleicaoBullyAnel.bully.ProcessoBully;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Função principal: solicita ao usuário qual algoritmo de eleição deseja simular
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o algoritmo de eleição:");
        System.out.println("1 - Algoritmo Bully");
        System.out.println("2 - Algoritmo Anel");
        System.out.print("Digite a opção (1 ou 2): ");
        int opcao = scanner.nextInt();
        scanner.close();

        // Executa o algoritmo escolhido
        if (opcao == 1) {
            executarBully(); // Simula o algoritmo Bully
        } else if (opcao == 2) {
            executarAnel(); // Simula o algoritmo Anel
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void executarBully() throws InterruptedException {
        // Cria e inicia os processos para o algoritmo Bully
        EleicaoServiceBully eleicaoService = new EleicaoServiceBully();
        List<ProcessoBully> processos = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            processos.add(new ProcessoBully(i, processos, eleicaoService));
        }

        for (ProcessoBully p : processos) {
            p.start(); // Inicia cada processo (thread)
        }

        Thread.sleep(2000);
        System.out.println("\n--- Iniciando eleição com P5 ---\n");
        processos.get(4).iniciarEleicao(); // P5 inicia a eleição

        Thread.sleep(8000);
        System.out.println("\n--- CENÁRIO A: Falha no coordenador (P5) ---\n");
        processos.get(4).desativar(); // Simula falha do coordenador

        Thread.sleep(8000);
        System.out.println("\n--- CENÁRIO A: P5 retorna ao sistema ---\n");
        processos.get(4).ativar(); // Coordenador retorna
        processos.get(4).iniciarEleicao(); // Inicia nova eleição

        Thread.sleep(10000);
        System.out.println("\n--- CENÁRIO B: P5 e P4 falham simultaneamente ---\n");
        processos.get(3).desativar(); // Simula falha de P4
        processos.get(4).desativar(); // Simula falha de P5

        Thread.sleep(8000);
        System.out.println("\n--- FIM DA SIMULAÇÃO ---");
        for (ProcessoBully p : processos) {
            p.encerrar(); // Encerra todos os processos
        }
    }

    private static void executarAnel() throws InterruptedException {
        // Cria e inicia os processos para o algoritmo Anel
        Monitor monitor = new Monitor(5);
        monitor.iniciar(); // Inicia o monitor e os processos

        Thread.sleep(2000);

        System.out.println("\n--- CENÁRIO A: Falha no coordenador (Anel) ---\n");
        monitor.falharProcesso(monitor.getCoordenador()); // Simula falha do coordenador

        Thread.sleep(8000);

        System.out.println("\n--- CENÁRIO A: Coordenador retorna ao sistema ---\n");
        monitor.recuperarProcesso(4); // Coordenador retorna
        new AlgoritmoAnel(monitor).iniciarEleicao(); // Inicia nova eleição

        Thread.sleep(10000);

        System.out.println("\n--- CENÁRIO B: Múltiplos processos falham ---\n");
        monitor.falharProcesso(3); // Simula falha de P3
        monitor.falharProcesso(4); // Simula falha de P4

        Thread.sleep(8000);
        System.out.println("\n--- FIM DA SIMULAÇÃO ---");
    }
}
