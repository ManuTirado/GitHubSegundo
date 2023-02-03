
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Utilidades {
    /***
     * Función que valida la entrada de un entero en un rango dado
     * @param min mínimo incluido
     * @param max máximo incluido
     * @return entero validado en el rango dado
     */
    public static int validarEntero(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int numero;
        do {
            try {
                System.out.print("==> ");
                numero = sc.nextInt();
                if (numero < min || numero > max) {
                    System.out.println("Número fuera del rango, introduzca un entero entre " + min + " y " + max);
                }
            } catch (Exception e) {
                System.out.println("Valor no válido");
                // Le asigno al número un valor fuera del rango para que no salga del bucles
                numero = min - 1;
                sc.nextLine();
            }
        } while (numero < min || numero > max);
        return numero;
    }

    /***
     * Función que valida la entrada de un número entero
     * @return Número entero validado
     */
    public static int validarEntero() {
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        boolean correcto = false;
        do {
            try {
                System.out.print("==> ");
                numero = sc.nextInt();
                correcto = true;
            } catch (Exception e) {
                System.out.println("Valor no válido");
                sc.nextLine();
            }
        } while (!correcto);
        return numero;
    }

    /***
     * Función que valida la entrada de un número float
     * @return Número float validado
     */
    public static float validarFloat() {
        Scanner sc = new Scanner(System.in);
        float numero = 0;
        boolean correcto = false;
        do {
            try {
                System.out.print("==> ");
                numero = sc.nextFloat();
                correcto = true;
            } catch (Exception e) {
                System.out.println("Valor no válido");
                sc.nextLine();
            }
        } while (!correcto);
        return numero;
    }

    /***
     * Función que lee un String
     * @return String leído
     */
    public static String leerString() {
        Scanner sc = new Scanner(System.in);
        System.out.print("==> ");
        return sc.nextLine();
    }

    public static LocalDate leerFecha() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        LocalDate testDate;
        String date;
        do {
            System.out.print("==> ");
            String fecha = sc.nextLine();
            testDate = null;
            date = fecha;
            try {
                testDate = LocalDate.parse(date,df);
            } catch (Exception e) {
                System.out.println("invalid format");
            }
        } while (!df.format(testDate).equals(date));
        return testDate;
    }

    public static void mostrarMenu() {
        System.out.println();
        System.out.println("/  /  /  / IES RAIMON  /  /  /  /");
        System.out.println("1 - Nuevo Ingreso");
        System.out.println("2 - Nueva Pareja");
        System.out.println("3 - Nueva Actividad");
        System.out.println("4 - Nuevo Entrenamiento");
        System.out.println("5 - Actualiza Mejor");
        System.out.println("6 - Mostrar Experiencia Acumulada");
        System.out.println("7 - Mostrar Ranking");
        System.out.println("0 - Salir");
        System.out.println("/  /  /  /  /  /  /  / /  /  /  /");
        System.out.println();
    }
}
