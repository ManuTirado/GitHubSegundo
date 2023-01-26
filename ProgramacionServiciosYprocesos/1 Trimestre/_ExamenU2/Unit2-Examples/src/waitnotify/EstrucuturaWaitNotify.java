package waitnotify;

public class EstrucuturaWaitNotify {
	
	public static Object objetoBloqueo = new Object();
	public static boolean condicionParaOperacion = true;

	public static void main(String[] args) {
		
		synchronized(objetoBloqueo) { //Acceso a recurso con exclusión mutua
			while(!condicionParaOperacion) { // Depende de estado de objetoBloqueo
				try {
					objetoBloqueo.wait(); //Espera a que alguien cambie de estado y notifique
				} catch(InterruptedException ex) {
					
				}
			}
			
			// Ya se puede realizar la operación
			// No puede interferir otro hilo desde comprobación de condición
			// en while(), porque todo está dentro de un bucle synchronized,
			// en el que solo se cede el control con wait()
			realizar_operacion();
			
			// Si como consecuencia de realizar la operación pudiera continuar otro hilo,
			// habría que notificar que el recurso se ha liberado
			objetoBloqueo.notifyAll(); // También se podría utilizar objetoBloqueo.notify();
			
		}
	}
	
	public static void realizar_operacion() {}
	
}
