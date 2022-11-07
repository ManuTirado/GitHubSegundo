package lanzaprocesocapturasalida;

import java.util.Arrays;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class LanzaProcesoCapturaSalida {

  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Debe indicarse comando a ejecutar.");
      System.exit(1);
    }

    ProcessBuilder pb = new ProcessBuilder(args);
    try {
      Process p = pb.start();
      try (InputStream is = p.getInputStream();
              InputStreamReader isr = new InputStreamReader(is);
              BufferedReader br = new BufferedReader(isr)) {
        int codRet = p.waitFor();
        System.out.println("La ejecución de " + Arrays.toString(args)
                + " devuelve " + codRet
                + " " + (codRet == 0 ? "(ejecución correcta)" : "(ERROR)")
        );
        System.out.println("Salida del proceso");
        System.out.println("------------------");
        String linea = null;
        while ((linea = br.readLine()) != null) {
          System.out.println(linea);
        }
        System.out.println("------------------");
      }
    } catch (IOException e) {
      System.err.println("Error durante ejecución del proceso");
      System.err.println("Información detallada");
      System.err.println("---------------------");
      e.printStackTrace();
      System.err.println("---------------------");
      System.exit(2);
    } catch (InterruptedException ex) {
      System.err.println("Proceso interrumpido");
      System.exit(3);
    }

  }

}
