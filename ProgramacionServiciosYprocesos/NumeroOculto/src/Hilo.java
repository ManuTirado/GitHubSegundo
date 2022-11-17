

public class Hilo extends Thread {

    public static int numeroOculto;
    public static int comprobacion = 0;

    public synchronized int propuestaNumero(int propuesta) {
        if (comprobacion == 1) {
            comprobacion = -1;
        }
        if (numeroOculto == propuesta) {
            comprobacion = 1;
        }

        return comprobacion;
    }

    @Override
    public void run() {
        int num = (int) (Math.random() * 100 + 1);
        while (propuestaNumero(num) != -1) {
            if (propuestaNumero(num) == 1) {
                System.out.println("Encontrado por el " + Thread.currentThread().getName());
                Thread.currentThread().interrupt();
            }
            num = (int) (Math.random() * 100 + 1);
        }
        Thread.currentThread().interrupt();
    }

    public static void main(String[] args) {
        numeroOculto = (int) (Math.random() * 100 + 1);

        Hilo hilo1 = new Hilo();
        hilo1.setName("Hilo1");
        Hilo hilo2 = new Hilo();
        hilo2.setName("Hilo2");
        Hilo hilo3 = new Hilo();
        hilo3.setName("Hilo3");
        Hilo hilo4 = new Hilo();
        hilo4.setName("Hilo4");
        Hilo hilo5 = new Hilo();
        hilo5.setName("Hilo5");
        Hilo hilo6 = new Hilo();
        hilo6.setName("Hilo6");
        Hilo hilo7 = new Hilo();
        hilo7.setName("Hilo7");
        Hilo hilo8 = new Hilo();
        hilo8.setName("Hilo8");
        Hilo hilo9 = new Hilo();
        hilo9.setName("Hilo9");
        Hilo hilo10 = new Hilo();
        hilo10.setName("Hilo10");

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();
        hilo8.start();
        hilo9.start();
        hilo10.start();

    }
}
