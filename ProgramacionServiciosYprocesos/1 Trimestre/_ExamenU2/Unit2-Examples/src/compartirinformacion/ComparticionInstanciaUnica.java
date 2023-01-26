package compartirinformacion;

public class ComparticionInstanciaUnica extends Thread {

	private ObjetoComun oc;
	
	
	public ComparticionInstanciaUnica(ObjetoComun oc) {
		super();
		this.oc = oc;
	}

	@Override
	public void run() {
		this.oc.variableComun++;
		System.out.println("Variable común: " + this.oc.variableComun);
		System.out.println("Hilo: " + Thread.currentThread().getName());
	}

	public static void main(String[] args) throws InterruptedException {
		ObjetoComun oc = new ObjetoComun();
		ComparticionInstanciaUnica ciu1 = new ComparticionInstanciaUnica(oc);
		ComparticionInstanciaUnica ciu2 = new ComparticionInstanciaUnica(oc);
		
		ciu1.setName("Hilo 1");
		ciu1.start();
		
		//Thread.sleep(100);
		ciu2.setName("Hilo 2");
		ciu2.start();
	}

}
