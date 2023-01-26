package Ej11;

import java.io.IOException;


/***
 * Crea una clase que lance los siguientes procesos y que sincronice la ejecución entre ellos:
 *      - Proceso 1: Creación por línea de comandos un fichero en blanco en la carpeta “carpeta1” que se creó en el ejercicio anterior.
 *                   Llama al fichero “fichero2.txt”.
 *      - Proceso 2: Abre el fichero creado en el Proceso 1 con el bloc de notas (el comando es notepad).
 *                   Antes de abrir el fichero tiene que esperar a que termine el Proceso 1.
 *      - Proceso 3: Debe lanzar una clase Java que se encargue de leer el contenido del fichero “fichero2.txt” y mostrar
 *                   el contenido del fichero por pantalla. Antes de lanzar esta clase Java debe esperar a que termine de ejecutarse el Proceso 2.
 *
 * Cuando pongas en ejecución esta clase, en el momento en el que se abra el bloc de notas debes escribir un mensaje,
 * que será el que se muestre cuando se lance el Proceso 3. Hasta que no cierres el bloc de notas no va a ejecutarse el Proceso 3.
 */
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
