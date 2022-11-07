package ejercicio2;

import java.util.Scanner;

public class CuentaLineas {

	public static void main(String[] args) {
		// Variable donde guardaremos el número de líneas
		int contador = 0;
		
		// Creamos el Scanner
		Scanner sc = new Scanner(System.in);
		
		// Mientras haya líneas para leer
		while(sc.hasNextLine()) {
			sc.nextLine(); // De esta forma avanzamos una línea en el fichero
			contador++;
		}
		
		System.out.println("El fichero tiene " + contador +  " líneas.");
		sc.close();
	}

}