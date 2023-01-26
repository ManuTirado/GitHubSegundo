package Ej1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Barberia implements Runnable {
    private static final int NUM_BARBEROS = 2;
    private static final int NUM_SILLAS = 4;

    private static Semaphore semaforoBarbero = new Semaphore(NUM_BARBEROS);
    private static Semaphore semaforoSillas = new Semaphore(NUM_SILLAS);


    /***
     * Entra en la barbería y compruebo si hay sillas libres, en cuyo caso obtengo el permiso correspondiente y espero a que
     * algún barbero quede libre para pelarle. En cuanto queda libre un barbero libero la silla y ocupo al barbero, y cuando
     * acaba de pelarle libero al barbero y lo notifico.
     */
    public synchronized void entrarEnLaBarberia() {
        String ansiRed = "\u001B[31m", ansiReset = "\u001B[0m", ansiGreen = "\u001B[32m", ansiYellow = "\u001B[33m", ansiBlue = "\u001B[34m";
        if (semaforoSillas.availablePermits() == 0) {   // Si no hay sillas libres
            System.out.println(ansiRed + "El cliente " + Thread.currentThread().getName() + " se ha marchado al no haber sillas libres" + ansiReset);
        } else {    //Si hay sillas libres
            try {   //Obtiene una silla
                semaforoSillas.acquire();
                System.out.println(ansiYellow + "El cliente " + Thread.currentThread().getName() + " se ha sentado a esperar" + ansiReset);
            } catch (InterruptedException e) {
                System.err.println("Error al intentar obtener un permiso para sentar al cliente");
                throw new RuntimeException(e);
            }
            try { // Espero a que quede libre algún barbero
                while (semaforoBarbero.availablePermits() <= 0) {
                    wait();
                }
                semaforoSillas.release();
            } catch (InterruptedException e) {
                System.err.println("Error en la espera de un barbero");
                throw new RuntimeException(e);
            }
            try {   //Obtengo el permiso de un barbero
                semaforoBarbero.acquire();
                System.out.println(ansiBlue + "El cliente " + Thread.currentThread().getName() + " se ha sentado a pelar" + ansiReset);
            } catch (InterruptedException e) {
                System.err.println("Error al intentar obtener un permiso para pelar al cliente");
                throw new RuntimeException(e);
            }
            try {   // Pelando
                Thread.sleep((long) (Math.random() * 2000 + 2000));
            } catch (InterruptedException e) {
                System.err.println("Error al intentar dormir el hilos");
                throw new RuntimeException(e);
            }
            semaforoBarbero.release();  //Libero al barbero

            System.out.println(ansiGreen + "El cliente " + Thread.currentThread().getName() + " ha terminado de pelarse" + ansiReset);
            notifyAll();    // Notifico al resto de hilos en espera
        }

    }


    @Override
    public void run() {
        entrarEnLaBarberia();
    }

    public static void main(String[] args) {
        ArrayList<Thread.State> estadosHilo = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Thread hilo = new Thread(new Barberia());
            hilo.setName("Cliente " + i);
            hilo.start();
            /*
            estadosHilo.add(hilo.getState());
            System.out.println("El cliente " + hilo.getName() + " ha llegado a la barbería");
            hilo.start();
            System.out.println(hilo.getName());
            while(hilo.getState()!=Thread.State.TERMINATED) {
                if(!estadosHilo.contains(hilo.getState())) {
                    estadosHilo.add(hilo.getState());
                }

            }

            if(!estadosHilo.contains(hilo.getState())) {
                estadosHilo.add(hilo.getState());
            }

            for(Thread.State estado : estadosHilo) {
                System.out.println(estado);
            }
*/

        }
    }
}
