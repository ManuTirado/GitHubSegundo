package sincronizacion;

/**
 * En este ejemplo, dos hilos creados a partir del mismo objeto ejecutan dos
 * métodos distintos. El primero de los hilos, al realizar la mitad de la tarea
 * entra en espera hasta que el segundo de los hilos finaliza y notifica que
 * debe reanudar la ejecución.
 *
 */
public class WaitNotifySimple implements Runnable {

	private static volatile boolean ejecutandoMetodo1 = false;

	public synchronized void metodo1() {
		for (int i = 0; i < 10; i++) {
			System.out.printf("Ejecución método 1 %d\n", i);
			if (i == 5) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void metodo2() {
		for (int i = 10; i < 20; i++) {
			System.out.printf("Ejecución método 2 %d\n", i);
		}
		this.notifyAll();
	}

	@Override
	public void run() {
		if (!ejecutandoMetodo1) {
			ejecutandoMetodo1 = true;
			metodo1();
		} else {
			metodo2();
		}
	}

	public static void main(String[] args) {
		WaitNotifySimple wns1 = new WaitNotifySimple();
		WaitNotifySimple wns2 = new WaitNotifySimple();
		new Thread(wns1).start();
		new Thread(wns2).start();
	}

}
