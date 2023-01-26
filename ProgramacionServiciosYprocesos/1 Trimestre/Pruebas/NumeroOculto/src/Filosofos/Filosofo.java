package Filosofos;

public class Filosofo extends Thread {
    public Mesa mesa;
    public int filosofo;

    public Filosofo (Mesa mesa, int filosofo) {
        this.mesa = mesa;
        this.filosofo = filosofo;
    }

    public void pensar() {
        System.out.println("Filosofo " + (this.filosofo + 1) + " pensando");
        try {
            Thread.sleep((long) (Math.random()*3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void comer() {
        System.out.println("Filosofo " + (this.filosofo + 1) + " comiendo");
        try {
            Thread.sleep((long) (Math.random()*3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        while (true) {
            pensar();
            this.mesa.cogerPalillos(this.filosofo);
            comer();
            this.mesa.dejarPalillos(this.filosofo);
        }
    }
}
