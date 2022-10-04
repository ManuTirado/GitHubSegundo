package Ej5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.ProcessBuilder.Redirect;

public class lanzaPreguntaNombre {

    private static final String RUTA_INPUT = "src\\Ej5\\input.txt";

    public static void main(String[] args) {
        String[] comando = {"java","src\\ej5\\PreguntaNombre.java"};

        File input = new File(RUTA_INPUT);
        ProcessBuilder pb = new ProcessBuilder(comando);

        pb.redirectInput(input);
        pb.redirectOutput(Redirect.INHERIT);

        try {
            Process p = pb.start();
            p.waitFor();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
