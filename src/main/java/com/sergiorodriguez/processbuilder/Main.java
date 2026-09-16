package com.sergiorodriguez.processbuilder;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 1: comando sin argumentos (dir) ===");
        Ejercicio1.comandoValido();

        System.out.println("\n=== Ejercicio 1: comando inexistente ===");
        Ejercicio1.comandoInexistente();

        System.out.println("\n=== Ejercicio 2: comando con argumentos (dir /w) ===");
        Ejercicio2.comandoValido();

        System.out.println("\n=== Ejercicio 2: comando inexistente con argumentos ===");
        Ejercicio2.comandoInexistente();

        System.out.println("\n=== Ejercicio 3: comando pedido por consola ===");
        Ejercicio3.pedirComandoYEjecutar();
    }
}
