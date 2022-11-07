package lanzaprocesoescribeaentrada;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class LanzaProcesoEscribeAEntrada {

  public static void main(String[] args) {

    ProcessBuilder pb = new ProcessBuilder("nslookup");
    pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    try (InputStreamReader isstdin = new InputStreamReader(System.in, "UTF-8"); // Leelínea a  línea de entrada estándar
            BufferedReader brstdin = new BufferedReader(isstdin)) {
      String linea;
      System.out.print("Introducir nombre de dominio: ");
      while ((linea = brstdin.readLine()) != null && linea.length() != 0) {
        Process p = pb.start();  // Lanza el proceso
        try (OutputStream osp = p.getOutputStream();
                OutputStreamWriter oswp = new OutputStreamWriter(osp, "UTF-8")) {
          oswp.write(linea); // Envía línea leida al proceso
        }
        try {
          p.waitFor();
        } catch (InterruptedException ex) {
        }
        System.out.print("Introducir nombre de dominio: ");
      }
    } catch (IOException e) {
      System.out.println("ERROR: de E/S");
      e.printStackTrace();
    }

  }

}
