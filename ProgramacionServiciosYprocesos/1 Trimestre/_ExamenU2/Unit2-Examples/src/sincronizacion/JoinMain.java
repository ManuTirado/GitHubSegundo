package sincronizacion;

public class JoinMain extends Thread {

	private int id;
	
	public JoinMain(int id) {
		super();
		this.id = id;
	}

	@Override
	public void run() {
		try {
			for(int i=0; i<3; i++) {
				System.out.printf("Hilo %d. Iteración %d \n", id, i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JoinMain hilo1 = new JoinMain(1);
		JoinMain hilo2 = new JoinMain(2);
		hilo1.start();
		hilo2.start();
		
		try {
			hilo1.join();
			hilo2.join();
			System.out.println("Se ha terminado la ejecución de los dos hilos");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
