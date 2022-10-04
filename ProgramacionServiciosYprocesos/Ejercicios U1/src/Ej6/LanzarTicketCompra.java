package Ej6;

import java.io.File;

public class LanzarTicketCompra {

    private static final String RUTA_OUTPUT = "src\\Ej6\\salida.txt";

    public static void main(String[] args) {
        String[] comando = {"java","src\\ej6\\TicketCompra.java"};

        File output = new File(RUTA_OUTPUT);
        ProcessBuilder pb = new ProcessBuilder(comando);

        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(output));

        try {
            Process p = pb.start();
            p.waitFor();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
