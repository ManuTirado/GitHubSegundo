package Ej2;

public class HiloProductor implements Runnable {
    final Contenedor cont;

    HiloProductor(Contenedor cont) {
        this.cont = cont;
    }

    @Override
    public void run() {
        while (!this.cont.finColecta()) {
            try {
                Thread.sleep((long) (Math.random()*191+10));    //Espero entre 10 y 200 ms
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (this.cont) {
                int colecta = (int) (Math.random() * 22 + 4);   // Produce entre 4 y 25€
                System.out.printf("Hilo %s ha recolectado %s €.\n", Thread.currentThread().getName(), colecta);
                this.cont.add(colecta);
                this.cont.notify();     // Notifico a los que estén esperando
            }
        }
        System.out.println("Se ha teminado de recolectar, se han recolectado " + this.cont.consultColectaTotal() + "€");
    }
}
