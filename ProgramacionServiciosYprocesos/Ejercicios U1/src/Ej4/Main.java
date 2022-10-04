package Ej4;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("java","src\\Ej2\\ProcesoLento.java");
        File salida = new File("src\\Ej4\\log.txt");

        try {
        if (!salida.exists()) {
            salida.createNewFile();
        }
        processBuilder.redirectOutput(salida);

            Process process = processBuilder.start();

            while (process.isAlive()) {
                System.out.println("¡¡ Está vivooooo !!");
                Thread.sleep(3000);
            }
            System.out.println("C murió ;(");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}