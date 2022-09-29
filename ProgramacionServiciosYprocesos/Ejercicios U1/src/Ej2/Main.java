package Ej2;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("java","src\\Ej2\\ProcesoLento.java");
        processBuilder.inheritIO();
        try {
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