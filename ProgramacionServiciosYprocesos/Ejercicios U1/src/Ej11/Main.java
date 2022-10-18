package Ej11;

import java.io.IOException;

public class Main {

    private static final String[] comando1 = {"cmd", "/C", "type", "nul", ">", "C:\\pruebas\\carpeta1\\fichero2.txt"};
    private static final String[] comando2 = {"notepad", "C:\\pruebas\\carpeta1\\fichero2.txt"};
    private static final String[] comando3 = {"java", "src\\Ej11\\LeerFichero.java"};

    public static void main(String[] args) {
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        ProcessBuilder pb3 = new ProcessBuilder(comando3);
        pb1.inheritIO();
        pb2.inheritIO();
        pb3.inheritIO();
        try {
            Process p1 = pb1.start();
            p1.waitFor();
            Process p2 = pb2.start();
            p2.waitFor();
            Process p3 = pb3.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
