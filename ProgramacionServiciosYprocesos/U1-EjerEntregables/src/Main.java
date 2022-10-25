import java.io.IOException;
import java.util.Scanner;

/***
 * Crea un programa que muestre un menú al usuario como el siguiente:
 * Elija qué comando desea ejecutar:
 * 1. Crear carpeta
 * 2. Crear fichero
 * 3. Mostrar contenido del directorio
 * Atendiendo a la opción seleccionada por el usuario, el programa debe lanzar un proceso u otro.
 * Toma de base el archivo Ejercicio1.java que se adjunta a la tarea.
 */

public class Main {

    private static final int OPC_SALIDA_MENU = 0;
    private static final int OPC_MIN_MENU = 1;
    private static final int OPC_MAX_MENU = 3;

    public static void main(String[] args) {
        int opcionEscogida;

        do {
            mostrarMenu();
            opcionEscogida = validarEnteroMenu(OPC_MIN_MENU, OPC_MAX_MENU, OPC_SALIDA_MENU);
            switch (opcionEscogida) {
                case 1:
                    crearCarpeta();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 0:
                    System.out.println("Adios!! 👋👋");
                    break;
            }



        } while (opcionEscogida != OPC_SALIDA_MENU);
    }

    private static void crearCarpeta() {
        ProcessBuilder processBuilder;
        Scanner sc = new Scanner(System.in);
        String rutaCarpeta;
        String[] comando;

        System.out.println("Ingrese la ruta en la que quiere crear la carpeta:");
        rutaCarpeta = sc.nextLine();
        sc.close();
        comando = new String[]{"cmd", "/C", "md", rutaCarpeta};

        processBuilder = new ProcessBuilder(comando);
        processBuilder.inheritIO();

        try {
            Process process = processBuilder.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * Procedimiento que muestra por consola el menú principal
     */
    private static void mostrarMenu () {
        System.out.println();
        System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- --");
        System.out.println("Elija qué comando desea ejecutar:");
        System.out.println("1. Crear carpeta");
        System.out.println("2. Crear fichero");
        System.out.println("3. Mostrar contenido del directorio");
        System.out.println("0. SALIR");
        System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- --");
    }

    /***
     * Función que valida la entrada de un entero en un rango dado
     * @param min mínimo incluido
     * @param max máximo incluido
     * @param salida número incluido
     * @return
     */
    private static int validarEnteroMenu(int min, int max, int salida) {
        Scanner sc=new Scanner(System.in);
        int numero=-1;
        do {
            try {
                System.out.print("==> ");
                numero= sc.nextInt();
                if ((numero<min || numero>max) && numero!=salida) {
                    System.out.println("Número fuera del rango, introduzca un entero entre " + min + " y " + max + ", ó " + salida);
                }
            } catch (Exception e) {
                System.out.println("Valor no válido");
                sc.nextLine();
            }
        } while ((numero < min || numero > max) && numero != salida );
        return numero;
    }
}