package seccioncritica;

public class Impresor extends Thread {

	private int contador;
	private static int acumulador;
	
	private void ajustaContador() {
		contador -= 500;
	}
	
	private static void ajustaAcumulador() {
		acumulador -= 500;
	}
	
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			contador++;
			acumulador++;
			if(contador==600) {
				ajustaContador();
			}
			if(acumulador==600) {
				ajustaAcumulador();
			}
			System.out.println("contador: " + contador);
			System.out.println("acumulador: " + acumulador);
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			Impresor imp = new Impresor();
			imp.start();
			
		}
		
	}

}
