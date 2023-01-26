package Ej2;

import java.util.Scanner;

public class CuentaLineas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numLineas = 0;

        while (sc.hasNextLine()) {      // Mientras haya una línea
            numLineas++;
            sc.nextLine();      // Paso a la siguiente línea
        }
        sc.close();
        System.out.print(numLineas);
    }
}
