package compartirinformacion;

public class ComparticionRunnable implements Runnable {
	private int contador;

	@Override
	public void run() {
		contador++;
		System.out.println(contador);
	}

	public static void main(String[] args) throws InterruptedException {
		ComparticionRunnable cb = new ComparticionRunnable();
		new Thread(cb).start();
		Thread.sleep(100);
		new Thread(cb).start();
	}

}
