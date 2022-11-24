import java.util.concurrent.Semaphore;

public class Main implements Runnable {
    public static Semaphore semaforoCarniceria = new Semaphore(4);
    public static Semaphore semaforoCharcuteria = new Semaphore(2);
    private boolean atendidoCarniceria = false;
    private boolean atendidoCharcuteria = false;

    public void carniceria() {
        try {
            semaforoCarniceria.acquire();   // Adquirimos un permiso para pasar por el semáforo
            System.out.println(Thread.currentThread().getName() + " pidiendo en la carnicería");
            Thread.sleep(10000);    // 10 segs
            System.out.println(Thread.currentThread().getName() + " ha terminado en la carnicería");
            semaforoCarniceria.release();   // Dejamos libre el permiso del semáforo que estabamos ocupando
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void charcuteria() {
        try {
            semaforoCharcuteria.acquire();  // Adquirimos un permiso para pasar por el semáforo
            System.out.println(Thread.currentThread().getName() + " pidiendo en la charcutería");
            Thread.sleep(10000);    // 10 segs
            System.out.println(Thread.currentThread().getName() + " ha terminado en la charcutería");
            semaforoCharcuteria.release();  // Dejamos libre el permiso del semáforo que estabamos ocupando
        } catch (InterruptedException e) {
            System.err.println("Error, se ha interrumpido el hilo con código de error: " +  e.getMessage());
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
            if (semaforoCharcuteria.availablePermits() > 0 && !atendidoCharcuteria) {
                charcuteria();
                atendidoCharcuteria = true;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new Thread(new Main());
            hilo.setName("Hilo " + i);
            hilo.start();
        }

    }

}