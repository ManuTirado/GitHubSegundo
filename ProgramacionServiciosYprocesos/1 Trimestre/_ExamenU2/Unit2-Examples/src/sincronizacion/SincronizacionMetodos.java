package sincronizacion;

public class SincronizacionMetodos implements Runnable {

	public synchronized void metodo1() {
		System.out.println("Comienzo del método 1");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			return;
		}
		System.out.println("Fin del método 1");
	}

	public synchronized void metodo2() {
		System.out.println("Comienzo del método 2");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			return;
		}
		System.out.println("Fin del método 2");
	}

	@Override
	public void run() {
		metodo1();
		metodo2();
	}

	public static void main(String[] args) {
		// Al utilizar el mismo objeto para lanzar dos hilos distintos, los métodos
		// declarados como synchronized bloquean el objeto al completo, no pudiendo
		// ejecutar otro método declarado como synchronized una vez hay otro en
		// ejecución
		SincronizacionMetodos sm = new SincronizacionMetodos();
		new Thread(sm).start();
		new Thread(sm).start();

		// ¿Qué ocurriría si creamos dos objetos distintos y lanzamos un hilo por cada
		// objeto?
	}

}
