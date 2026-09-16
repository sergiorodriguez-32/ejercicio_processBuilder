package com.sergiorodriguez.processbuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio3 {

    public static void pedirComandoYEjecutar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un comando (ej: dir, dir /w): ");
        String comando = scanner.nextLine();

        // toda la linea se pasa tal cual a cmd.exe /c, que la interpreta el mismo
        ejecutarComando("cmd.exe", "/c", comando);
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
