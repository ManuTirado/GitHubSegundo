package prioridadhilos;

import java.util.ArrayList;

class HiloConPrioridad extends Thread {

  long contador = 0;

  public long getContador() {
    return contador;
  }

  private void incContador() {
    contador++;
  }

  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      this.incContador();
    }
  }

}

public class PrioridadDeHilos {

  public static void main(String[] args) {

    ArrayList<Integer> prioridades = new ArrayList<Integer>();
    for (int i = Thread.MIN_PRIORITY; i <= Thread.MAX_PRIORITY; i++) {
      prioridades.add(i);
    }

    try {

      ArrayList<HiloConPrioridad> hilos = new ArrayList<HiloConPrioridad>();
      for (Integer prior : prioridades) {  // Crea hilos
        HiloConPrioridad hilo = new HiloConPrioridad();
        hilo.setPriority(prior);
        hilos.add(hilo);
      }

      for (Thread hilo : hilos) {  // Arranca hilos
        hilo.start();
      }

      int duracionSegundos = 10;
      System.out.printf("Se deja correr los hilos durante %d segundos.\n", duracionSegundos);
      Thread.sleep(1000 * duracionSegundos);        // Deja correr hilos durante un tiempo.

      for (Thread hilo : hilos) {  // Interrumpe hilos
        hilo.interrupt();
      }

      for (HiloConPrioridad hilo : hilos) {  // Muestra cuenta para cada hilo
        System.out.printf("Contador para proceso con prioridad %d: %d\n", hilo.getPriority(), hilo.getContador());
      }

    } catch (InterruptedException ex) {
      System.out.println("Hilo principal interrumpido.");
    }

  }

}
