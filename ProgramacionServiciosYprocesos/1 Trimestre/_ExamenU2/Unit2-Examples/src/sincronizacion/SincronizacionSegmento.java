package sincronizacion;

/**
 * En este ejemplo se puede observar que el contenido de los dos métodos están
 * bloqueados, solo un hilo lo puede ejecutar a la vez, pero sí se pueden
 * ejecutar varios métodos a la vez, porque en este caso los métodos no están
 * declarados como synchronized.
 * 
 * Se pueden ejecutar los dos métodos a la vez también porque el objeto de
 * bloqueo es distinto, si fuese el mismo, entonces sólo se podría ejecutar un
 * método cada vez.
 * 
 */
public class SincronizacionSegmento extends Thread {
	int id;
	static Object bloqueo1 = new Object();
	static Object bloqueo2 = new Object();

	public SincronizacionSegmento(int id) {
		super();
		this.id = id;
	}

	public void metodo1() {
		synchronized (bloqueo1) {
			System.out.println("Comienzo del método 1 del hilo " + id);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
			System.out.println("Fin del método 1 del hilo " + id);
		}
	}

	//
	public void metodo2() {
		synchronized (bloqueo2) {
			System.out.println("Comienzo del método 2 del hilo " + id);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
			System.out.println("Fin del método 2 del hilo " + id);
		}
	}

	@Override
	public void run() {
		if (id == 1) {
			metodo1();
			metodo2();
		} else {
			metodo2();
			metodo1();
		}
	}

	public static void main(String[] args) {
		new SincronizacionSegmento(1).start();
		new SincronizacionSegmento(2).start();
	}

}
