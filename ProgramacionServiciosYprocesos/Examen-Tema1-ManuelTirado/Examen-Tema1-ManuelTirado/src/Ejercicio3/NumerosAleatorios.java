package Ejercicio3;

import java.text.DecimalFormat;

public class NumerosAleatorios {
	
	private static final DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			System.out.println(df.format(Math.random()*100+1));
		}
	}

}
