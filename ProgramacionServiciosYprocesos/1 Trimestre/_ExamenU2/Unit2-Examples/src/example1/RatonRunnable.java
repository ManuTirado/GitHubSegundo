package example1;

public class RatonRunnable implements Runnable {
	private String nombre;
	private int tiempoAlimentacion;
	
	public RatonRunnable(String nombre, int tiempoAlimentacion) {
		super();
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
	}

	public void comer() {
		try {
			System.out.printf("El ratón %s ha comenzado a alimentarse%n", nombre);
			Thread.sleep(tiempoAlimentacion * 1000);
			System.out.printf("El ratón %s ha terminado de alimentarse%n", nombre);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.comer();
	}
	
	public static void main(String[] args) {
		RatonRunnable fievel = new RatonRunnable("Fievel", 4);
		RatonRunnable jerry = new RatonRunnable("Jerry", 5);
		RatonRunnable pinky = new RatonRunnable("Pinky", 3);
		RatonRunnable mickey = new RatonRunnable("Mickey", 6);
		
		// Los objetos Runnable deben envolverse en objetos Thread para ser arrancados por start()
		// El método start pertenece a Thread, de ahí la envoltura del objeto.
		// ¿Y si llamamos directamente a run()?
		new Thread(fievel).start();
		new Thread(jerry).start();
		new Thread(pinky).start();
		new Thread(mickey).start();
	}

}
