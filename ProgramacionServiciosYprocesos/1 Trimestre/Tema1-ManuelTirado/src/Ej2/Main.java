package Ej2;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/***
 * Crea una clase que lance los siguientes procesos y que sincronice la ejecución entre ellos:
 *      - Proceso 1: Debe abrir la aplicación bloc de notas. El usuario debe escribir en él una serie de líneas.
 *                   Cuando termine de escribir, guardará el fichero y cerrará el bloc de notas.
 *                   El fichero a guardar debe llamarse numLineas.txt y guardarse en la carpeta C:\ejercicio2.
 *      - Proceso 2: Debe lanzar una clase Java que se llamará CuentaLineas.java.
 *
 *                   La clase CuentaLineas.java debe leer de la entrada estándar una serie de líneas (no sabemos cuántas)
 *                   y devolver el número de líneas que ha leído.
 *
 *                   El proceso 2 debe redireccionar la entrada estándar de forma que esta sea el fichero numLineas.txt creado por el Proceso 1.
 *                   La salida estándar y la de error del Proceso 2 deben redirigirse hacia las del proceso padre (es decir, la consola).
 *
 *                   El Proceso 2 debe esperar a que el Proceso 1 termine para así poder leer del fichero, pero,
 *                   si el Proceso 1 tarda más de 30 segundos en terminar, se debe de terminar la ejecución del proceso,
 *                   mostrando el mensaje de error correspondiente.
 */
public class Main {
    public static void main(String[] args) {
        File fichero = new File("C:\\ejercicio2\\numLineas.txt");

        String[] comando1 = {"notepad", fichero.getAbsolutePath()};
        String[] comando2 = {"java", "src\\Ej2\\CuentaLineas.java"};

        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        // Redirijo los errores al padre (consola)
        pb1.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb2.redirectError(ProcessBuilder.Redirect.INHERIT);

        pb2.redirectInput(fichero);     // Redirijo la entrada al fichero
        pb2.redirectOutput(ProcessBuilder.Redirect.INHERIT);        // Redirijo la salida al padre (consola)

        crearFichero(fichero);

        try {
            Process process1 = pb1.start();     // Inicio el proceso

            if (process1.waitFor(30, TimeUnit.SECONDS)) { // Si el proceso termina antes de 30 segundos
                System.out.print("El fichero " + fichero.getName() + " tiene: ");
                Process process2 = pb2.start();     // Inicio el segundo proceso y espero a que acabe
                int p2retorno = process2.waitFor();
                System.out.println(" líneas.");
                // Compruebo cómo ha terminado el proceso 2 y escribo un mensaje en consecuencia
                if (p2retorno == 0) {
                    System.out.println("El proceso \"" + comando2 + "\" ha finalizado correctamente");
                } else {
                    System.out.println(
                            "El proceso \"" + comando2 + "\" ha terminado con el siguiente código de error: " + p2retorno);
                }
            } else {
                // Si tarda más de 30 segundos matamos el proceso
                process1.destroy();
                // Si devuelve false el método waitFor significa que no lo hemos esperado
                System.out.println("Ha tardado más de 30 segundos, no se ha lanzado el proceso 2");
            }
            int p1retorno = process1.exitValue();
            // Compruebo cómo ha terminado el proceso 1 y escribo un mensaje en consecuencia
            if (p1retorno == 0) {
                System.out.println("El proceso \"" + comando1 + "\" ha finalizado correctamente");
            } else {
                System.out.println(
                        "El proceso \"" + comando1 + "\" ha terminado con el siguiente código de error: " + p1retorno);
            }

        } catch (IOException e) {
            System.out.println("Error en la entrada/salida del proceso");
            System.out.println(e.getMessage());
            // Indicamos que la ejecución termina con error 1
            System.exit(1);
        } catch (InterruptedException e) {
            System.out.println("Ejecución del proceso interrumpida");
            System.out.println(e.getMessage());
            // Indicamos que la ejecución termina con error 2
            System.exit(2);
        }
    }

    /***
     * Proceso que, si no existe, crea el arhivo pasado como File por parámetro.
     * Si no existen las carpetas en la ruta, las crea.
     * Si existe el fichero lo borra y lo crea de nuevo.
     * @param file fichero a crear
     */
    private static void crearFichero(File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error en la creación del fichero");
            System.out.println(e.getMessage());;
        }
    }
}
