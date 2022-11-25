import java.util.concurrent.Semaphore;

public class Main implements Runnable {
    public static Semaphore semaforo = new Semaphore(4);
    private boolean leidos = false;

    public void libro() {
        try {
            semaforo.acquire(2);   // Adquirimos 2 permisos para pasar por el semáforo
            System.out.println(Thread.currentThread().getName() + " pidiendo en la carnicería");
            Thread.sleep(10000);    // 10 segs
            System.out.println(Thread.currentThread().getName() + " ha terminado en la carnicería");
            semaforoCarniceria.release();   // Dejamos libre el permiso del semáforo que estabamos ocupando
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    /***
     * Mientras el hilo no haya sido atendido tanto por la carnicería como por la charcutería,
     * comprobamos si hay algún hueco en alguno y en tal caso lo atendemos
     */
    public void run() {
        while (!atendidoCarniceria || !atendidoCharcuteria) {
            if (semaforoCarniceria.availablePermits() > 0 && !atendidoCarniceria) {
                carniceria();
                atendidoCarniceria = true;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            Thread hilo = new Thread(new Main());
            hilo.setName("Estudiante " + i);
            hilo.start();
        }

    }
}