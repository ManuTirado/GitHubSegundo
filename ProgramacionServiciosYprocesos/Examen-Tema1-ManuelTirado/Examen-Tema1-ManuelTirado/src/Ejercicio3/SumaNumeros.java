package Ejercicio3;

import java.util.Scanner;

public class SumaNumeros {

	public static void main(String[] args) {
		double suma = 0;
		double numero = 0;
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNextDouble()) {
			numero = sc.nextDouble();
			suma += numero;
		}
		
		System.out.println(args[0] + " => " + suma);
		
		sc.close();
	}

}
