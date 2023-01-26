package lanzaproceso;

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
    
    /* Con esta llamada hacemos que el proceso herede la entrada 
    y salida estándares del proceso padre */
    pb.inheritIO();

    try {
    // Arrancamos el proceso
      Process p = pb.start();
      
    /* Se espera a que termine la ejecución del proceso hijo y se obtiene el código de retorno.
     * Si durante la espera se interrumple la ejecución del programa, se lanzaría una excepción
     * de tipo InterruptedException. Este programa la capturaría y la informaría.
     */
      int codRet = p.waitFor(); 
      System.out.println("La ejecución de " + Arrays.toString(args)
              + " devuelve " + codRet
              + " " + (codRet == 0 ? "(ejecución correcta)" : "(ERROR)")
      );
    } catch (IOException e) {
      System.err.println("Error durante ejecución del proceso");
      System.err.println("Información detallada");
      System.err.println("---------------------");
      e.printStackTrace();
      System.err.println("---------------------");
      // Indicamos que la ejecución termina con error 2
      System.exit(2);
    } catch (InterruptedException e) {
      System.err.println("Proceso interrumpido");
      // Indicamos que la ejecución termina con error 2
      System.exit(3);
    }

  }

}
