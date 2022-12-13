package Filosofos;

public class Main {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();

        for (int i = 0; i < 5; i++) {
            Thread hilo = new Filosofo(mesa,i);
            hilo.start();
        }
    }
}
