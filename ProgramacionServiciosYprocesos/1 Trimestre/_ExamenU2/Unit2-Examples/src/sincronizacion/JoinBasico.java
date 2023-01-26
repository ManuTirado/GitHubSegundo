package sincronizacion;

public class JoinBasico extends Thread {

	private int id;
	private boolean suspender = false;
	private Thread hiloReferencia;
	
	
	public JoinBasico(int id) {
		super();
		this.id = id;
	}

	public void suspenderHilo(Thread hiloReferencia) {
		this.suspender=true;
		this.hiloReferencia=hiloReferencia;
	}
	
	@Override
	public void run() {
		try {
			for(int i=0; i<3; i++) {
				if(suspender) {
					hiloReferencia.join();
				}
				System.out.printf("Hilo %d. Iteración %d \n", id, i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JoinBasico hilo1 = new JoinBasico(1);
		JoinBasico hilo2 = new JoinBasico(2);
		hilo1.start();
		hilo2.start();
		hilo2.suspenderHilo(hilo1);
	}

}
