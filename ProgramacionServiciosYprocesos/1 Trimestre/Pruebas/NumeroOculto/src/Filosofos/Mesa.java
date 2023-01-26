package Filosofos;

public class Mesa {
    public static boolean[] palillos = new boolean[5];

    public int  palilloIzquierda (int filosofo) {
        return filosofo;
    }
    public int  palilloDerecha (int filosofo) {
        if (filosofo == 0) {
            return (palillos.length-1);
        } else {
            return (filosofo -1);
        }
    }

    public synchronized void cogerPalillos (int filosofo) {
        if (palillos[palilloIzquierda(filosofo)] || palillos[palilloDerecha(filosofo)]) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        palillos[palilloIzquierda(filosofo)] = true;
        palillos[palilloDerecha(filosofo)] = true;
        System.out.println("El filosofo " + (filosofo+1) + " ha cogido los palillos " + palilloIzquierda(filosofo) + " y " + palilloDerecha(filosofo));
    }

    public synchronized void dejarPalillos (int filosofo) {
        palillos[palilloIzquierda(filosofo)] = false;
        palillos[palilloDerecha(filosofo)] = false;
        System.out.println("El filosofo " + (filosofo+1) + " ha dejado los palillos " + palilloIzquierda(filosofo) + " y " + palilloDerecha(filosofo));
        notifyAll();
    }
}
