package EleicaoBully;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o algoritmo de eleição:");
        System.out.println("1 - Algoritmo Bully");
        System.out.println("2 - Algoritmo Anel");
        System.out.print("Digite a opção (1 ou 2): ");
        int opcao = scanner.nextInt();
        scanner.close();

        Monitor monitor = new Monitor(5); // 5 processos: IDs 0 a 4
        monitor.iniciar();

        Thread.sleep(2000);

        if (opcao == 1) {
            executarBully(monitor);
        } else if (opcao == 2) {
            executarAnel(monitor);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Simula cenários com o algoritmo de Bully
    public static void executarBully(Monitor monitor) throws InterruptedException {
        System.out.println("\n--- CENÁRIO A: Falha no coordenador (Bully) ---\n");

        Processo coordenador = monitor.getProcesso(monitor.getCoordenador());
        monitor.falharProcesso(coordenador.getIdProcesso());

        Thread.sleep(2000);

        Processo detector = monitor.getProcesso(0);
        EleicaoService bully = new EleicaoService();
        bully.iniciarEleicao(detector, monitor.getProcessos());

        Thread.sleep(8000);

        System.out.println("\n--- CENÁRIO A: Coordenador retorna ao sistema ---\n");
        monitor.recuperarProcesso(4);
        bully.iniciarEleicao(monitor.getProcesso(4), monitor.getProcessos());

        Thread.sleep(8000);

        System.out.println("\n--- CENÁRIO B: Falhas simultâneas ---\n");
        monitor.falharProcesso(3);
        monitor.falharProcesso(4);

        Thread.sleep(2000);
        Processo detector2 = monitor.getProcesso(1);
        bully.iniciarEleicao(detector2, monitor.getProcessos());

        Thread.sleep(5000);
        System.out.println("\n--- FIM DA SIMULAÇÃO ---");
    }

    // Simula cenários com o algoritmo de Anel
    public static void executarAnel(Monitor monitor) throws InterruptedException {
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
