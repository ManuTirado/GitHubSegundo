package example2;

import java.util.ArrayList;

import example1.RatonRunnable;

public class States {

	public static void main(String[] args) {
		RatonRunnable mickey = new RatonRunnable("Mickey", 6);
		ArrayList<Thread.State> estadosHilo = new ArrayList<>();
		Thread h = new Thread(mickey);
		estadosHilo.add(h.getState());
		h.start();
		System.out.println(h.getName());
		while(h.getState()!=Thread.State.TERMINATED) {
			if(!estadosHilo.contains(h.getState())) {
				estadosHilo.add(h.getState());
			}
			
		}
		
		if(!estadosHilo.contains(h.getState())) {
			estadosHilo.add(h.getState());
		}
		
		for(Thread.State estado : estadosHilo) {
			System.out.println(estado);
		}
	}
	
	// ¿Qué ocurriría si quitamos la llamada a Sleep dentro del código del hilo? 
	// ¿Qué diferencias se aprecian en los estados por los que ha transitado el hilo?
	// ¿A qué se deben dichas diferencias?
	

}
