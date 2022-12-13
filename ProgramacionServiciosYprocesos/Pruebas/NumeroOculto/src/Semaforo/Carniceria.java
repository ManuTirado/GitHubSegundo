package Semaforo;

import java.util.concurrent.Semaphore;

public class Carniceria extends Thread {
    public static Semaphore semaphore;

    public Carniceria (int numEmpleados) {
        semaphore = new Semaphore(numEmpleados);
    }

    public void pedir() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " pidiendo en la carnicería");
            Thread.sleep((long) (Math.random()*5000));
            System.out.println(Thread.currentThread().getName() + " ha terminado de pedir en la carnicería");
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        pedir();
        Thread.currentThread().interrupt();
    }
}
