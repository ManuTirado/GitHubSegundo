package interrupciones;

/**
 * En este ejemplo se lanzan dos hilos. Ambos pueden ser interrumpidos. 
 * Uno porque realiza llamadas a sleep() de la clase Thread y gestiona la
 * InterruptedException. 
 * El otro hilo comprueba periódicamente si ha sido
 * interrumpido con el método isInterrupted() de la clase Thread.
 *
 */
class HiloInterrSleep implements Runnable {

	String nombre;

	public String getNombre() {
		return nombre;
	}

	HiloInterrSleep(String nombre) {
		this.nombre = nombre;
	}

	public void run() {
		try {
			while (true) {
				System.out.println(this.nombre);
				Thread.sleep(1000);
			}
		} catch (InterruptedException ex) {
			System.out.println("***INTERRUMPIDO*** " + this.getNombre());
		}
	}

}

class HiloInterrNoSleep implements Runnable {

	String nombre;

	public String getNombre() {
		return nombre;
	}

	HiloInterrNoSleep(String nombre) {
		this.nombre = nombre;
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			for (long i = 0; i < 1000000000L; i++) {
			}
			System.out.println(this.nombre);
		}
		System.out.println("***INTERRUMPIDO*** " + this.getNombre());
	}

}

public class InterrupcionDeHilos {

	public static void main(String[] args) {

		try {
			Thread h1 = new Thread(new HiloInterrSleep("H1"));
			Thread h2 = new Thread(new HiloInterrNoSleep("H2"));
			h1.start();
			h2.start();
			Thread.sleep(5000);
			h1.interrupt();
			h2.interrupt();
		} catch (InterruptedException ex) {
			System.out.println("Hilo principal interrumpido.");
		}
	}
}
