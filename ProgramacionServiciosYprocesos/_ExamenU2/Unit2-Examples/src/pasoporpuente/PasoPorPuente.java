package pasoporpuente;

/**
 *
 * programa que controle el paso de personas por un puente, siempre en la misma
 * dirección, para que se cumplan las siguientes restricciones. No pueden pasar
 * más de tres personas a la vez, y no puede haber más de 200 kg de peso en
 * ningún momento. En un sistema real, se podría obtener la información de las
 * personas que llegan y de su peso mediante sensores. Para realizar la
 * simulación, se va a modelar las personas como hilos. El tiempo entre la
 * llegada de dos personas es aleatorio entre 1 y 30 segundos,
 * para pasar entre 10 y 50 segundos, y las personas tienen un peso aleatorio
 * entre 40 y 120 kg.
 *
 */
import java.util.Random;

class Puente {  // Estado, objeto compartido entre los hilos

  private static final int PESO_MAXIMO = 200;
  private static final int MAX_PERSONAS = 3;
  private int peso = 0;
  private int numPersonas = 0;

  synchronized public int getPeso() {
    return peso;
  }

  synchronized public int getNumPersonas() {
    return numPersonas;
  }


  synchronized public boolean autorizacionPaso(Persona persona) {

    boolean result;

    if (this.peso + persona.getPeso() <= Puente.PESO_MAXIMO && this.numPersonas < Puente.MAX_PERSONAS) {
      this.numPersonas++;
      this.peso += persona.getPeso();
      result = true;
    } else {
      result = false;
    }
    return result;
  }

  synchronized public void terminaPaso(Persona persona) {
    this.peso -= persona.getPeso();
    this.numPersonas--;
  }
}

class Persona implements Runnable {
  private final Puente puente;

  private final String idPersona;
  private final int peso;
  private final int tMinPaso, tMaxPaso;

  public int getPeso() {
    return peso;
  }

  Persona(Puente puente, int peso, int tMinPaso, int tMaxPaso, String idP) {
    this.puente = puente;
    this.peso = peso;
    this.tMinPaso = tMinPaso;
    this.tMaxPaso = tMaxPaso;
    this.idPersona = idP;
  }

  @Override
  public void run() {

    System.out.printf("- %s de %d kg quiere cruzar, en puente %d kg, %d persona%s.\n",
            this.idPersona, this.peso, puente.getPeso(), puente.getNumPersonas(), puente.getNumPersonas() != 1 ? "s" : "");

    // Espera para conseguir autorización
    boolean autorizado = false;
    while (!autorizado) {
      synchronized (this.puente) {
        autorizado = this.puente.autorizacionPaso(this);
        if (!autorizado) {
          try {
            System.out.printf("# %s debe esperar.\n", this.idPersona);
            this.puente.wait();
          } catch (InterruptedException ex) {
            System.out.printf("Interrupción mientras %s espera para cruzar.\n", this.idPersona);
          }
        }
      }
    }

    System.out.printf("> %s con peso %d puede cruzar, puente soporta peso %d, con %d personas.\n",
            this.idPersona, this.getPeso(), this.puente.getPeso(), puente.getNumPersonas(), this.puente.getNumPersonas() != 1 ? "s" : "");

    // Pasa al puente, y tarda un tiempo en cruzar
    Random r = new Random();
    int tiempoPaso = this.tMinPaso + r.nextInt(this.tMaxPaso - this.tMinPaso + 1);
    try {
      System.out.printf("%s va a tardar tiempo %d en cruzar.\n", this.idPersona, tiempoPaso);
      Thread.sleep(1000*tiempoPaso);
    } catch (InterruptedException ex) {
      System.out.printf("Interrupción mientras %s pasa.\n", this.idPersona);
    }

    // Sale del puente
    synchronized (this.puente) {
      this.puente.terminaPaso(this);
      System.out.printf("< %s sale del puente, puente soporta peso %d, %d persona%s.\n",
              this.idPersona, this.puente.getPeso(), this.puente.getNumPersonas(), this.puente.getNumPersonas() != 1 ? "s" : "");
      puente.notifyAll();
    }
  }
}

public class PasoPorPuente {

  public static void main(String[] args) {

    final Puente puente = new Puente();

    int tMinParaLlegadaPersona = 1;
    int tMaxParaLlegadaPersona = 30;
    int tMinPaso = 10;
    int tMaxPaso = 50;
    int minPesoPersona = 40;
    int maxPesoPersona = 120;

    System.out.println(">>>>>>>>>>>> Comienza simulación.");
    Random r = new Random();
    int idPersona = 1;

    while (true) {

      int tParaLlegadaPersona = tMinParaLlegadaPersona + r.nextInt(
              tMaxParaLlegadaPersona - tMinParaLlegadaPersona + 1);
      int pesoPersona = minPesoPersona + r.nextInt(
              maxPesoPersona - minPesoPersona + 1);

      System.out.printf("Siguiente persona llega en %d segundos.\n", tParaLlegadaPersona);

      try {
        Thread.sleep(1000*tParaLlegadaPersona);
      } catch (InterruptedException ex) {
        System.out.printf("Interrumpido proceso principal");
      }

      Thread hiloPersona = new Thread(new Persona(puente, pesoPersona, tMinPaso, tMaxPaso, "P"+idPersona));
      hiloPersona.start();

      idPersona++;

    }

  }

}
