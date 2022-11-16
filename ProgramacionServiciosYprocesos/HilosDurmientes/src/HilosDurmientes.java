import java.util.Random;

public class HilosDurmientes extends Thread{

    private String nombre;

    public HilosDurmientes (String Nombre) {
        this.nombre = Nombre;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Soy el bucle "+ this.nombre +" y estoy trabajando");
            try {
                Thread.sleep((long)((int)(Math.random()*10+1))*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


