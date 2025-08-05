package EleicaoBully;

/**
 * Classe principal para simulação do algoritmo de eleição por anel.
 * Executa dois cenários de falha e recuperação de processos.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Monitor monitor = new Monitor(5); // 5 processos: IDs 0 a 4
        monitor.iniciar();

        // Aguarda 2 segundos e mostra coordenador inicial
        Thread.sleep(2000);

        // Cenário A: Coordenador falha e retorna
        System.out.println("\n--- CENÁRIO A: Falha no coordenador ---\n");
        monitor.falharProcesso(monitor.getCoordenador());

        // Aguarda 8 segundos para eleição
        Thread.sleep(8000);

        System.out.println("\n--- CENÁRIO A: Coordenador retorna ao sistema ---\n");
        monitor.recuperarProcesso(4); // Supondo que o maior ID é 4
        new AlgoritmoAnel(monitor).iniciarEleicao();

        // Aguarda 10 segundos antes do cenário B
        Thread.sleep(10000);

        // Cenário B: Falha de múltiplos processos
        System.out.println("\n--- CENÁRIO B: Múltiplos processos falham ---\n");
        monitor.falharProcesso(3);
        monitor.falharProcesso(4);

        // Aguarda 8 segundos para nova eleição
        Thread.sleep(8000);

        System.out.println("\n--- FIM DA SIMULAÇÃO ---\n");
    }
}