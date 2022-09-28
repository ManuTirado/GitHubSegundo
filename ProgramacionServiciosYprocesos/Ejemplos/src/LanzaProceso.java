import java.io.File;
import java.util.Arrays;
import java.io.IOException;

public class LanzaProceso {

    public static void main(String[] args) {

        // Comprobamos que se ha introducido al menos un comando
        if (args.length <= 0) {
            System.out.println("Debe indicarse comando a ejecutar.");
            // Terminamos la ejecución del programa con valor 1
            System.exit(1);
        }

        // Le pasamos los argumentos al ProcessBuilder
        ProcessBuilder pb = new ProcessBuilder(args);
        File directorio = new File("src");
        pb.directory(directorio);

    /* Con esta llamada hacemos que el proceso herede la entrada 
    y salida estándares del proceso padre */
        pb.inheritIO();

        try {
            // Arrancamos el proceso
            Process p = pb.start();

            /* Se espera a que termine la ejecuciÃ³n del proceso hijo y se obtiene el cÃ³digo de retorno.
             * Si durante la espera se interrumple la ejecuciÃ³n del programa, se lanzarÃ­a una excepciÃ³n
             * de tipo InterruptedException. Este programa la capturarÃ­a y la informarÃ­a.
             */
            int codRet = p.waitFor();
            System.out.println("La ejecuciÃ³n de " + Arrays.toString(args)
                    + " devuelve " + codRet
                    + " " + (codRet == 0 ? "(ejecuciÃ³n correcta)" : "(ERROR)")
            );
        } catch (IOException e) {
            System.err.println("Error durante ejecuciÃ³n del proceso");
            System.err.println("InformaciÃ³n detallada");
            System.err.println("---------------------");
            e.printStackTrace();
            System.err.println("---------------------");
            // Indicamos que la ejecuciÃ³n termina con error 2
            System.exit(2);
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido");
            // Indicamos que la ejecuciÃ³n termina con error 2
            System.exit(3);
        }

    }

}
