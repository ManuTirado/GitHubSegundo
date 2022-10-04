package Ej5;

import java.util.Scanner;

public class PreguntaNombre {
    public static void main(String[] args) {
        String nombre;
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        nombre = sc.nextLine();

        System.out.printf("\n¡Hola %s!",nombre);

        sc.close();
    }
}
