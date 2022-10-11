package Ej9;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        File input = new File("src\\Ej9\\input.txt");

        String[] comando = {"java","src\\Ej5\\PreguntaNombre.java"};

        try {
            BufferedReader ReaderSalidaProceso;
            BufferedReader ReaderEntradaProceso;

            Process p = Runtime.getRuntime().exec(comando);

            ReaderEntradaProceso = new BufferedReader(new FileReader(input));
            String entrada = ReaderEntradaProceso.readLine();
            p.getOutputStream().write((entrada + "\n").getBytes());

            ReaderSalidaProceso = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = ReaderSalidaProceso.readLine()) != null) {
                System.out.println(line);
            }

            ReaderSalidaProceso.close();
            ReaderEntradaProceso.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
