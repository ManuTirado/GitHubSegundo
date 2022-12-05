public class Mesa {

    private boolean[] palillos;     // Array que representa a los palillos, true -> en uso

    /**
     * Constructor
     * @param palillos Número de palillos en la mesa
     */
    public Mesa(int palillos) {
        this.palillos = new boolean[palillos];
    }

    /**
     * Devuelve el índice correspondiente al índice del palillo de la derecha del filósofo
     * @param filosofo Índice del filósofo
     * @return
     */
    public int palilloDerecha(int filosofo) {
        int pos;
        if (filosofo == this.palillos.length - 1) {
            pos = 0;
        } else {
            pos = filosofo + 1;
        }
        return pos;
    }

    public synchronized void cogerPalillos(int filosofo) {
        while (palillos[filosofo] || palillos[palilloDerecha(filosofo)]) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        palillos[filosofo] = true;
        palillos[palilloDerecha(filosofo)] = true;
    }

    public synchronized void dejarPalillos(int filosofo) {
        palillos[filosofo] = false;
        palillos[palilloDerecha(filosofo)] = false;
        notifyAll();
    }
}
