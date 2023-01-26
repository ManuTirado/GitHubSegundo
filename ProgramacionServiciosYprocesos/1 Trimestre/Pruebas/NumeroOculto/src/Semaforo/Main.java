package Semaforo;

public class Main {
    public static void main(String[] args) {
        Carniceria carniceria = new Carniceria(4);
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new Thread(carniceria);
            hilo.setName("Cliente " + i);
            hilo.start();
        }
    }
}
