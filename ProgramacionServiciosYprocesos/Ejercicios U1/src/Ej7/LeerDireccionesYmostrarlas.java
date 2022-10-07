package Ej7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeerDireccionesYmostrarlas {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String ip, letraClase;
        int clase;

        while (sc.hasNextLine()) {
            ip = sc.nextLine();
            clase = Integer.parseInt(ip.split("\\.")[0]);
            if (clase >= 0 && clase < 128) {
                letraClase = "A";
            } else if (clase >= 128 && clase < 192) {
                letraClase = "B";
            } else if (clase >= 192 && clase < 224) {
                letraClase = "C";
            } else {
                letraClase = "Error";
            }
            System.out.println(ip + " => clase " + letraClase);
        }
        sc.close();
    }
}
