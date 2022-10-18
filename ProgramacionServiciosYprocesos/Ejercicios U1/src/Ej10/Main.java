package Ej10;

public class Main {

    private static final String[] comando1 = {"cmd", "/C", "md", "C:\\pruebas\\carpeta1"};
    private static final String[] comando2 = {"cmd", "/C", "type", "nul", ">", "C:\\pruebas\\carpeta1\\fichero1.txt"};
    private static final String[] comando3 = {"notepad", "C:\\pruebas\\carpeta1\\fichero1.txt"};

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

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
