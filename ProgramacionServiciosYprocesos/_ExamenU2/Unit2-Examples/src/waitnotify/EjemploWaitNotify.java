package waitnotify;

public class EjemploWaitNotify implements Runnable {

	public static Tenedor t = new Tenedor();
	public boolean comido;

	@Override
	public void run() {
		try {

			synchronized (t) {
				while (!comido && t.isEnPosesion()) {
					t.wait();
				}

				t.setEnPosesion(true);
				System.out.println(Thread.currentThread().getName() + " está comiendo");
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " ha terminado de comer");
				comido = true;
				t.setEnPosesion(false);
				t.notifyAll();

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			EjemploWaitNotify ewn = new EjemploWaitNotify();

			Thread hilo = new Thread(ewn);
			hilo.setName("Comensal " + i);
			hilo.start();
		}
	}

}
