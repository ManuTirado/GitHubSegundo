import java.util.concurrent.Semaphore;

public class Main implements Runnable{
    public static Semaphore semaforo = new Semaphore(4);
    private boolean atendido = false;

    public void carniceria() {
        try {
            semaforo.acquire();   // Adquirimos un permiso para pasar por el semáforo
            System.out.println(Thread.currentThread().getName() + " pidiendo en la carnicería");
            Thread.sleep(10000);    // 10 segs
            System.out.println(Thread.currentThread().getName() + " ha terminado en la carnicería");
            semaforo.release();   // Dejamos libre el permiso del semáforo que estabamos ocupando
        } catch (InterruptedException e) {
            System.err.println("Error, se ha interrumpido el hilo con código de error: " +  e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    /***
     * Mientras el hilo no haya sido atendido por la carnicería,
     * comprobamos si hay algún hueco en el semáforo y en caso afirmativo, lo atendemos
     */
    public void run() {
        while (!atendido) {
            if (semaforo.availablePermits() > 0 && !atendido) {
                carniceria();
                atendido = true;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new Thread(new Main());
            hilo.setName("Cliente " + i);
            hilo.start();
        }

    }
}