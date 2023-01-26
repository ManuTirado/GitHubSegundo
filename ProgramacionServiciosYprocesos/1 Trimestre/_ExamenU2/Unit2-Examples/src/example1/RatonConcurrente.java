package example1;

import java.util.concurrent.Semaphore;

public class RatonConcurrente extends Thread {
	private String nombre;
	private int tiempoAlimentacion;
	private static Semaphore semaforo = new Semaphore(2);
	
	public RatonConcurrente(String nombre, int tiempoAlimentacion) {
		super();
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
	}

	public void comer() {
		try {
			semaforo.acquire();
			System.out.printf("El ratón %s ha comenzado a alimentarse%n", nombre);
			Thread.sleep(tiempoAlimentacion * 1000);
			System.out.printf("El ratón %s ha terminado de alimentarse%n", nombre);
			semaforo.release();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.comer();
	}
	
	public static void main(String[] args) {
		RatonConcurrente fievel = new RatonConcurrente("Fievel", 4);
		RatonConcurrente jerry = new RatonConcurrente("Jerry", 5);
		RatonConcurrente pinky = new RatonConcurrente("Pinky", 3);
		RatonConcurrente mickey = new RatonConcurrente("Mickey", 6);
		RatonConcurrente fievel2 = new RatonConcurrente("Fievel2", 4);
		RatonConcurrente jerry2 = new RatonConcurrente("Jerry2", 5);
		RatonConcurrente pinky2 = new RatonConcurrente("Pinky2", 3);
		RatonConcurrente mickey2 = new RatonConcurrente("Mickey2", 6);
		
		fievel.setPriority(MAX_PRIORITY);
		jerry.setPriority(MAX_PRIORITY);
		pinky.setPriority(MAX_PRIORITY);
		
		fievel2.setPriority(MAX_PRIORITY);
		jerry2.setPriority(MAX_PRIORITY);
		//pinky2.setPriority(MAX_PRIORITY);
		mickey2.setPriority(MIN_PRIORITY);
		mickey.setPriority(MIN_PRIORITY);
		// El método start() pertenece a la clase Thread. Por dentro, llama al método run()
		fievel.start(); 
		jerry.start();
		pinky.start();
		mickey.start();
		
		fievel2.start(); 
		jerry2.start();
		pinky2.start();
		mickey2.start();
	}

}
