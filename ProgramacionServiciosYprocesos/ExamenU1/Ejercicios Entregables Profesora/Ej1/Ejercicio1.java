package ejercicio1;

import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {

        int opc = menu();
        String comando = creaComando(opc);

        // Creamos el ProcessBuilder con el comando creado en creaComando.
        // Hacemos un split para que me devuelva un array de String, que es lo que espera el
        // constructor de ProcessBuilder
        ProcessBuilder pb = new ProcessBuilder(comando.split(" "));

        // Heredamos tanta la entrada estándar como la salida estándar del proceso padre.
        pb.inheritIO();

        try {
            // Arrancamos el proceso
            Process p = pb.start();

            // Obtenemos el valor devuelto por el proceso
            int retorno = p.waitFor();

            // Compruebo cómo ha terminado el proceso y escribo un mensaje en consecuencia
            if (retorno == 0) {
                System.out.println("El proceso ha finalizado correctamente");
            } else {
                System.out.println(
                        "El comando " + comando + " ha terminado con el siguiente código de error: " + retorno);
            }
        } catch (IOException e) {
            System.err.println("Error durante ejecución del proceso");
            System.err.println("Información detallada");
            System.err.println("---------------------");
            e.printStackTrace();
            System.err.println("---------------------");
            // Indicamos que la ejecución termina con error 2
            System.exit(2);
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido");
            // Indicamos que la ejecución termina con error 3
            System.exit(3);
        }

    }

    /**
     * Función que crea el comando a ejecutar
     *
     * @param opc La opción seleccionada por el usuario
     * @return Devuelve una cadena con el comando a ejecutar.
     */
    public static String creaComando(int opc) {

        // Según la opción introducida debemos lanzar un proceso u otro.
        // Todos los procesos son comandos de Windows, por lo que deben comenzar con cmd

        String comando = "cmd /C "; // En comando guardaremos el comando a ejecutar

        // Creamos variables para guardar la ruta de carpetas, así como para la carpeta
        // a crear o el fichero a crear según el caso
        String ruta, carpeta, fichero;

        Scanner sc = new Scanner(System.in);

        switch (opc) {

            case 1:
                // Si elige crear una carpeta, debo pedirle también la ruta donde quiere crearla
                // y el nombre de la carpeta

                System.out.println("Escriba la ruta donde quiere crear la carpeta:");
                ruta = sc.next();

                System.out.println("Indique ahora el nombre de carpeta a crear");
                carpeta = sc.next();

                // construimos el comando para crear una carpeta
                comando += "md " + ruta + "\\" + carpeta;
                break;

            case 2:
                // Si elige crear un fichero, debo pedirle también la ruta donde quiere crearlo
                // y el nombre del fichero
                System.out.println("Escriba la ruta donde quiere crear el fichero:");
                ruta = sc.next();

                System.out.println("Indique ahora el nombre del fichero a crear");
                fichero = sc.next();

                // construimos el comando para crear un fichero en una carpeta
                comando += "type nul > " + ruta + "\\" + fichero;
                break;

            case 3:
                // Si elige mostrar un directorio, debo pedirle también la ruta del directorio a
                // mostrar. Si lo deja vacío, debo mostrar el contenido del directorio actual
                System.out.println("Escriba la ruta de la carpeta a mostrar:");
                ruta = sc.nextLine();

                // construimos el comando para mostrar el contenido de una carpeta
                comando += "dir " + ruta;
                break;
            default:
                System.out.println("La opción introducida no es correcta");

                // Terminamos con código de error igual a 1
                System.exit(1);
        }
        sc.close();
        return comando;
    }

    /**
     * Función que muestra un menú al usuario
     * @return La opción seleccionada por el usuario
     */
    public static int menu() {
        // En opc guardaremos la opción seleccionada por el usuario
        int opc;
        Scanner sc = new Scanner(System.in);

        // Imprimimos el menú con las diversas opciones
        System.out.println("Elija qué comando desea ejecutar:");
        System.out.println("1. Crear carpeta");
        System.out.println("2. Crear fichero");
        System.out.println("3. Mostrar contenido del directorio");

        // Leemos la opción de teclado
        opc = sc.nextInt();
//		sc.close();

        return opc;
    }
}