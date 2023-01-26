package sincronizacion;

/**
 * En este programa, 1000 hilos incrementan en 1000 unidades una variable estática común.
 * La variable debería obtener como resultado 1 000 000.
 *
 * Si se ejecuta varias veces se puede observar que nunca se obtiene el resultado esperado.
 */
public class VariableCompartida extends Thread {

	private static int contador;
	
	@Override
	public void run() {
		for(int i=0; i<1000; i++) {
			incremento();
		}
	}
	
	public static synchronized void incremento() {
		contador++;
	}
	
	public static void main(String[] args) {
		for(int i=0; i<1000; i++) {
			new VariableCompartida().start();
		}
		try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Valor de contador:" + contador);
	}

}
