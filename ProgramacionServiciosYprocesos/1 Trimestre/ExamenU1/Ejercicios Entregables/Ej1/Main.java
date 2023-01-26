package Ej1;

import java.io.IOException;
import java.util.Scanner;

/***
 * Crea un programa que muestre un menú al usuario como el siguiente:
 * Elija qué comando desea ejecutar:
 *      1. Crear carpeta
 *      2. Crear fichero
 *      3. Mostrar contenido del directorio
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
                case 1 -> crearCarpeta();
                case 2 -> crearFichero();
                case 3 -> mostrarContenido();
                case 0 -> System.out.println("Adios!! 👋👋");
                default -> System.out.println("Error, no existe esta opción");
            }
        } while (opcionEscogida != OPC_SALIDA_MENU);
    }

    /**
     * Proceso que crea una carpeta en la ruta dada por el usuario, con el nombre
     * también especificado por el usuario.
     * En caso de no existir alguna carpeta en la ruta especificada, estas también se generaran.
     */
    private static void crearCarpeta() {
        ProcessBuilder processBuilder;
        Scanner sc = new Scanner(System.in);

        String rutaCarpeta;
        String nombreCarpeta;
        String[] comando;

        System.out.print("Ingrese la ruta en la que quiere crear la carpeta: ");
        rutaCarpeta = sc.nextLine();
        System.out.print("Ingrese el nombre de la carpeta: ");
        nombreCarpeta = sc.nextLine();

        comando = new String[]{"cmd", "/C", "md", rutaCarpeta+"\\"+nombreCarpeta};

        processBuilder = new ProcessBuilder(comando);
        processBuilder.inheritIO();     // Redirijo la entrada/salida del proceso al padre (consola)

        try {
            Process process = processBuilder.start();   // Inicio el proceso y espero que acabe
            int retorno = process.waitFor();
            if (retorno == 0) {
                System.out.println("El proceso ha finalizado correctamente");
            } else {
                System.out.println(
                        "El comando " + comando + " ha terminado con el siguiente código de error: " + retorno);
            }
        } catch (IOException e) {
            System.out.println("Error en la entrada/salida en la creación de la carpeta");
            System.out.println(e.getMessage());
            System.exit(2);
        } catch (InterruptedException e) {
            System.out.println("Error durante la creación de la carpeta, se ha interrumpido el proceso");
            System.out.println(e.getMessage());
            System.exit(3);
        }
    }

    /**
     * Proceso que crea un fichero con el nombre y la ruta especificada por el usuario.
     */
    private static void crearFichero() {
        ProcessBuilder processBuilder;
        Scanner sc = new Scanner(System.in);

        String rutaFichero;
        String nombreFichero;
        String[] comando;

        System.out.print("Ingrese la ruta en la que quiere crear el fichero: ");
        rutaFichero = sc.nextLine();
        System.out.print("Ingrese el nombre del fichero (y su extensión): ");
        nombreFichero = sc.nextLine();

        comando = new String[]{"cmd", "/C", "type", "nul", ">", rutaFichero+"\\"+nombreFichero};

        processBuilder = new ProcessBuilder(comando);
        processBuilder.inheritIO();     // Redirijo la entrada/salida del proceso al padre (consola)

        try {
            Process process = processBuilder.start();       // Inicio el proceso y espero que acabe
            int retorno = process.waitFor();
            if (retorno == 0) {
                System.out.println("El proceso ha finalizado correctamente");
            } else {
                System.out.println(
                        "El comando " + comando + " ha terminado con el siguiente código de error: " + retorno);
            }
        } catch (IOException e) {
            System.out.println("Error en la entrada/salida en la creación del fichero");
            System.out.println(e.getMessage());
            System.exit(2);
        } catch (InterruptedException e) {
            System.out.println("Error durante la creación del fichero, se ha interrumpido el proceso");
            System.out.println(e.getMessage());
            System.exit(3);
        }
    }

    /**
     * Proceso que muestra el contenido de un directorio especificado por el usuario.
     * En caso de no especificar ninguno, se muestra el contenido del directorio actual.
     */
    private static void mostrarContenido() {
        ProcessBuilder processBuilder;
        Scanner sc = new Scanner(System.in);

        String rutaFichero;
        String[] comando;

        System.out.print("Ingrese la ruta en la que quiere consultar: ");
        rutaFichero = sc.nextLine();

        comando = new String[]{"cmd", "/C", "DIR", rutaFichero};

        processBuilder = new ProcessBuilder(comando);
        processBuilder.inheritIO();     // Redirijo la entrada/salida del proceso al padre (consola)

        try {
            Process process = processBuilder.start();       // Inicio el proceso y espero que acabe
            int retorno = process.waitFor();
            if (retorno == 0) {
                System.out.println("El proceso ha finalizado correctamente");
            } else {
                System.out.println(
                        "El comando " + comando + " ha terminado con el siguiente código de error: " + retorno);
            }
        } catch (IOException e) {
            System.out.println("Error en la entrada/salida en la consulta de la carpeta");
            System.out.println(e.getMessage());
            System.exit(2);
        } catch (InterruptedException e) {
            System.out.println("Error durante la consulta de la carpeta, se ha interrumpido el proceso");
            System.out.println(e.getMessage());
            System.exit(3);
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
     * [min, max] ∪ salida
     * min debe ser menor que max
     * @param min mínimo incluido
     * @param max máximo incluido
     * @param salida número incluido
     * @return Número validado en el rango
     */
    private static int validarEnteroMenu(int min, int max, int salida) {
        Scanner sc=new Scanner(System.in);
        int numero;
        do {
            try {
                System.out.print("==> ");
                numero= sc.nextInt();
                if ((numero<min || numero>max) && numero!=salida) {
                    System.out.println("Número fuera del rango, introduzca un entero entre " + min + " y " + max + ", ó " + salida);
                }
            } catch (Exception e) {
                System.out.println("Valor no válido");
                // Le asigno al número un valor fuera del rango para que no salga del bucles
                numero = min-1;
                if (numero == salida) { // Si el valor coincide con el valor de la salida le asigno un número menos
                    numero--;
                }
                sc.nextLine();
            }
        } while ((numero < min || numero > max) && numero != salida );
        return numero;
    }
}