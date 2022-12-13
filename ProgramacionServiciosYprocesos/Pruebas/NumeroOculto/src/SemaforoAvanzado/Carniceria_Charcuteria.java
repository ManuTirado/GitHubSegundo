package SemaforoAvanzado;

import java.util.concurrent.Semaphore;

public class Carniceria_Charcuteria extends Thread {
    public static Semaphore semaphoreCarniceria = new Semaphore(4);
    public static Semaphore semaphoreCharcuteria = new Semaphore(2);;
    private boolean atendidoCarniceria;

    private boolean atendidoCharcuteria;

    public void pedirCarniceria() {
        try {
            semaphoreCarniceria.acquire();
            System.out.println(Thread.currentThread().getName() + " pidiendo en la carnicería");
            Thread.sleep((long) (Math.random()*5000));
            System.out.println(Thread.currentThread().getName() + " ha terminado de pedir en la carnicería");
            semaphoreCarniceria.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void pedirCharcuteria() {
        try {
            semaphoreCharcuteria.acquire();
            System.out.println(Thread.currentThread().getName() + " pidiendo en la charcutería");
            Thread.sleep((long) (Math.random()*5000));
            System.out.println(Thread.currentThread().getName() + " ha terminado de pedir en la charcutería");
            semaphoreCharcuteria.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (!atendidoCarniceria || !atendidoCharcuteria) {
            if (semaphoreCarniceria.availablePermits()>0 && !atendidoCarniceria) {
                pedirCarniceria();
                atendidoCarniceria = true;
            }
            if (semaphoreCharcuteria.availablePermits()>0 && !atendidoCharcuteria) {
                pedirCharcuteria();
                atendidoCharcuteria = true;
            }
        }

    }
}
