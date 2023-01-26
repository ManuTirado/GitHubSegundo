package Ej2;

public class Main {
    public static void main(String[] args) {
        // Creo la colecta general
        Contenedor colecta = new Contenedor();

        // Creo los hilos productores
        Thread hprod1 = new Thread(new HiloProductor(colecta));
        hprod1.setName("Productor1");
        hprod1.setPriority(1);
        hprod1.start();
        Thread hprod2 = new Thread(new HiloProductor(colecta));
        hprod2.setName("Productor2");
        hprod2.setPriority(4);
        hprod2.start();
        Thread hprod3 = new Thread(new HiloProductor(colecta));
        hprod3.setName("Productor3");
        hprod3.setPriority(7);
        hprod3.start();
        Thread hprod4 = new Thread(new HiloProductor(colecta));
        hprod4.setName("Productor4");
        hprod4.setPriority(10);
        hprod4.start();

        // Creo los hilos consumidores
        for (int i = 0; i < 4; i++) {
            Thread hcons = new Thread(new HiloConsumidor(colecta));
            hcons.setName("Consumidor" + (i+1));
            hcons.start();
        }
    }
}
