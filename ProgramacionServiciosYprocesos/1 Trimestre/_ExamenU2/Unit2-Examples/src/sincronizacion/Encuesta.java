package sincronizacion;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

class ResultadosEncuesta {

  private final HashMap<String, Integer> totalPorRespuesta = new HashMap<>();
  private final HashMap<String, Integer> totalPorZona = new HashMap<>();

  // Suma uno a número de respuestas para la zona y para la respuesta
  synchronized public void anotaRespuesta(String idZona, String respuesta) {
    Integer numRespValor = this.totalPorRespuesta.get(respuesta);
    this.totalPorRespuesta.put(respuesta, numRespValor == null ? 1 : numRespValor+1);
    Integer numRespZona = this.totalPorZona.get(idZona);
    this.totalPorZona.put(idZona, numRespZona == null ? 1 : numRespZona+1);
  }

  synchronized public Set<String> obtenZonas() {
    return this.totalPorZona.keySet();
  }

  synchronized public Set<String> obtenRespuestas() {
    return this.totalPorRespuesta.keySet();
  }

  synchronized public int obtenNumRespuestasZona(String zona) {
    return this.totalPorZona.get(zona);
  }

  synchronized public int obtenNumRespuestas(String respuesta) {
    return this.totalPorRespuesta.get(respuesta);
  }

}

class EncuestadorZona implements Runnable {

  private final String idZona;
  private final ResultadosEncuesta resultados;

  EncuestadorZona(String idZona, ResultadosEncuesta resultados) {
    this.idZona = idZona;
    this.resultados = resultados;
  }

  @Override
  public void run() {
    System.out.printf(">>Encuestador para zona %s comienza.\n", this.idZona);
    Random r = new Random();
    int numRespuestas = 100 + r.nextInt(200 - 100) + 1;
    for (int i = 0; i < numRespuestas; i++) {
      int numRespuesta = r.nextInt(10);   // Respuesta de 0 a 9, 0 es NS/NC
      String respuesta = null;
      if (numRespuesta > 0) {
        respuesta = "respuesta_" + numRespuesta;
      }
      this.resultados.anotaRespuesta(this.idZona, respuesta);
    }
    System.out.printf("##Encuestador para zona %s termina.\n", this.idZona);
  }

}

public class Encuesta {

  private static final int NUM_ZONAS = 20;

  public static void main(String[] args) {

    ResultadosEncuesta resultados = new ResultadosEncuesta();

    Thread[] encuestadores = new Thread[NUM_ZONAS];

    for (int i = 0; i < NUM_ZONAS; i++) {  // Crea hilos
      Thread encuestador = new Thread(new EncuestadorZona("zona" + (i + 1), resultados));
      encuestadores[i] = encuestador;
    }
    for (Thread encuestador : encuestadores) {  // Lanza hilos
      encuestador.start();
    }
    for (Thread encuestador : encuestadores) {  // Espera que terminen hilos
      try {
        encuestador.join();
      } catch (InterruptedException ex) {
      }
    }

    System.out.println("Encuestados por zonas");
    Set<String> zonas = resultados.obtenZonas();
    int granTotalPorZonas = 0;
    for (String zona : zonas) {
      int totalParaZona = resultados.obtenNumRespuestasZona(zona);
      System.out.printf("%s: %d\n", zona, totalParaZona);
      granTotalPorZonas += totalParaZona;
    }
    System.out.printf("TOTAL: %d\n", granTotalPorZonas);

    System.out.println("Resultados por respuesta:");
    Set<String> respuestas = resultados.obtenRespuestas();
    int granTotalPorRespuestas = 0;
    for (String respuesta : respuestas) {
      int totalParaRespuesta = resultados.obtenNumRespuestas(respuesta);
      System.out.printf("%s: %d\n", respuesta != null ? respuesta : "NS/NC", totalParaRespuesta);
      granTotalPorRespuestas += totalParaRespuesta;
    }
    System.out.printf("TOTAL: %d\n", granTotalPorRespuestas);

  }

}
