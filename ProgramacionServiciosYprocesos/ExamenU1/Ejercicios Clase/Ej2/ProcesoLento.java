package Ej2;

public class ProcesoLento {

	public static void main(String[] args) {
		for (int i=0; i<10; i++) {
			System.out.println(i+1);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
