package SemaforoAvanzado;

import Semaforo.Carniceria;

public class Main {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new Thread(new Carniceria_Charcuteria());
            hilo.setName("Cliente " + i);
            hilo.start();
        }
    }
}
