import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio1 {

    public static void comandoValido() {
        ejecutarComando("cmd.exe", "/c", "dir");
    }

    public static void comandoInexistente() {
        ejecutarComando("comandoquenoexiste");
    }

    private static void ejecutarComando(String... comando) {
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.redirectErrorStream(true);

        try {
            Process proceso = pb.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            int codigoSalida = proceso.waitFor();
            System.out.println("Codigo de salida: " + codigoSalida);

        } catch (IOException e) {
            System.out.println("Error: el comando no existe o no se pudo ejecutar -> " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("El proceso fue interrumpido.");
        }
    }
}
