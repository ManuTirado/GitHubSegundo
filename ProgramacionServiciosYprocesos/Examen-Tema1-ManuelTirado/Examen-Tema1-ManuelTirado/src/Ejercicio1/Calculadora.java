package Ejercicio1;

public class Calculadora {

    public static void main(String[] args) {
        int opcion = Integer.parseInt(args[0]);
        int numero1 = Integer.parseInt(args[1]);
        int numero2 = Integer.parseInt(args[2]);

        switch (opcion) {
            case 1:
                System.out.println("El resultado de la suma es: " + (numero1 + numero2));
                break;
            case 2:
                System.out.println("El resultado de la resta es: " + (numero1 - numero2));
                break;
            case 3:
                System.out.println("El resultado de la multiplicación es: " + (numero1 * numero2));
                break;
            case 4:
                System.out.println("El resultado de la división es: " + ((float) numero1 / (float) numero2));
                break;
            default:
                System.out.println("La opción introducida no es válida");
        }


    }
}
