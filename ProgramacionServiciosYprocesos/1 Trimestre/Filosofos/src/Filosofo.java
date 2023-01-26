public class Filosofo extends Thread {
    private Mesa mesa;
    private int filosofo;   // Índice del filósofo

    /**
     * Constructor
     * @param mesa Mesa en la que comen los filósofos
     * @param filosofo  Índice del filósofo
     */
    public Filosofo(Mesa mesa, int filosofo) {
        this.mesa = mesa;
        this.filosofo = filosofo;
    }

    public void pensar() {
        System.out.println("Filosofo " + (filosofo + 1) + " => pensando");
        try {
            sleep((int) (Math.random() * 3000));    // Tiempo que está pensando
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void comer() {
        this.mesa.cogerPalillos(this.filosofo);
        System.out.println("Filosofo " + (filosofo + 1) + " => comiendo");
        try {
            sleep((int) (Math.random() * 3000));    // Tiempo que tarda en comer
            this.mesa.dejarPalillos(this.filosofo);
            System.out.println("Filosofo " + (filosofo + 1) + " => terminado de comer");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        while (true) {
            this.pensar();
            this.comer();
        }
    }
}
