package sincronizacion;

import java.util.concurrent.Semaphore;

public class SemaforoBasico implements Runnable {

	Semaphore semaforo = new Semaphore(3);
	
	@Override
	public void run() {
		try {
			semaforo.acquire();
			System.out.println("Paso 1");
			Thread.sleep(1000);
			semaforo.release();
			
			semaforo.acquire();
			System.out.println("Paso 2");
			Thread.sleep(1000);
			semaforo.release();
			
			semaforo.acquire();
			System.out.println("Paso 3");
			Thread.sleep(1000);			
			semaforo.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 

	public static void main(String[] args) {
		SemaforoBasico sb = new SemaforoBasico();
		for(int i=0; i<5; i++) {
			new Thread(sb).start();
		}
	}

}
