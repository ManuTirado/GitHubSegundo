package Ej2;

public class HiloConsumidor implements Runnable {
    final Contenedor cont;

    HiloConsumidor(Contenedor cont) {
        this.cont = cont;
    }

    @Override
    public void run() {
        while (!this.cont.finColecta()) {
            try {   // Espero entre 20 y 300 ms
                Thread.sleep((long) (Math.random() * 281 + 20));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Segmento crítico
            synchronized (this.cont) {
                int numAleatorio = (int) (Math.random() * 31 + 10);     //Recoje entre 10 y 40 €
                System.out.printf("Hilo %s quiere %d €.\n", Thread.currentThread().getName(), numAleatorio);
                Integer dato = this.cont.consult();
                if (dato < numAleatorio) {  // Si lo que hay en la cesta es menor que lo que quiere recoger
                    while (dato < numAleatorio) {
                        try {
                            this.cont.wait();
                        } catch (InterruptedException ex) {
                        }
                        System.out.printf("Hilo %s quiere %d €.\n", Thread.currentThread().getName(), numAleatorio);
                        dato = this.cont.consult();
                    }
                    if (dato < numAleatorio) {
                        System.out.printf("Hilo %s quiere %d €, pero solo hay %d €.\n", Thread.currentThread().getName(), numAleatorio, dato);
                    } else {
                        this.cont.get(numAleatorio);
                        System.out.printf("Hilo %s consume %d €.\n", Thread.currentThread().getName(), numAleatorio);
                    }
                }


            }
        }
    }
}
