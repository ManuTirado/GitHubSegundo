package Ej10;


/***
 * Crea una clase que lance los siguientes procesos y que sincronice la ejecución entre ellos:
 *      - Proceso 1: Creación por línea de comandos de una carpeta de nombre “carpeta1” en la ruta C:\pruebas. Si esta carpeta no existe créala.
 *      - Proceso 2: Creación por línea de comandos un fichero en blanco en la carpeta creada en el Proceso 1. Llama al fichero “fichero1.txt”.
 *                   Deberá esperar a que termine correctamente el Proceso 1 antes de crear el fichero.
 *      - Proceso 3: Abre el fichero creado en el Proceso 2 con el bloc de notas (el comando es notepad).
 *                   Antes de abrir el fichero tiene que esperar a que termine el Proceso 2.
 */
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
