import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio2 {

    public static void comandoValido() {
        // /w es el argumento de dir (formato ancho)
        ejecutarComando("cmd.exe", "/c", "dir", "/w");
    }

    public static void comandoInexistente() {
        // comando inventado con argumento, para forzar el error
        ejecutarComando("comandoquenoexiste", "-x");
    }

    private static void ejecutarComando(String... comando) {
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.redirectErrorStream(true); // junta stdout y stderr en un unico flujo

        try {
            Process proceso = pb.start(); // inicia el proceso

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            int codigoSalida = proceso.waitFor(); // espera a que termine el proceso
            System.out.println("Codigo de salida: " + codigoSalida);

        } catch (IOException e) {
            // se lanza si el comando no existe
            System.out.println("Error: el comando no existe o no se pudo ejecutar -> " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("El proceso fue interrumpido.");
        }
    }
}
