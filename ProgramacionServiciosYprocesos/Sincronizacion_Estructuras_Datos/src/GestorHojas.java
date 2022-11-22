import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GestorHojas extends Thread {

    private static List<String> lista = new ArrayList<String>();
    private Semaphore mutex = new Semaphore(1);

    @Override
    public void run() {
        try {

            mutex.acquire();
            for (int i = 1; i <= 10; i++) {
                lista.add(new String("Texto " + i));
            }

            for (String string : lista) {
                System.out.println(string + " => " + Thread.currentThread().getName());
            }
            mutex.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        GestorHojas gh = new GestorHojas();
        for (int i = 0; i < 10; i++) {
            new Thread(gh).start();
        }

    }

}
