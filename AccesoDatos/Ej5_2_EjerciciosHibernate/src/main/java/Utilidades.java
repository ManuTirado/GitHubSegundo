
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

    /***
     * Imprime el menú principal por consola
     */
    public static void imprimirMenuPrincipal() {
        System.out.println("-- -- -- -- Seleccione una tabla -- -- -- --");
        System.out.println("1 - Mesa");
        System.out.println("2 - Factura");
        System.out.println("3 - Pedido");
        System.out.println("4 - Productos");
        System.out.println("0 - Salir");
    }

    /***
     * Imprime el menú sencundario por consola
     */
    public static void imprimirMenuSecundario() {
        System.out.println("-- -- -- -- ¿Qué desea hacer? -- -- -- --");
        System.out.println("1 - Insertar Registro");
        System.out.println("2 - Listar Registros");
        System.out.println("3 - Modificar Registro");
        System.out.println("4 - Borrar Registro");
        System.out.println("0 - Volver");
    }
}
