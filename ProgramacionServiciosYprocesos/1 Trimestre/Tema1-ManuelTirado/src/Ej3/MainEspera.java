package Ej3;

import java.io.IOException;


/***
 * Crea un programa que cuente las vocales de un fichero de texto que se adjunta a la tarea (fichero_texto.txt).
 * Para ello crea una clase que lance la clase CuentaCaracteres.java (se adjunta a la tarea) 5 veces en paralelo, una por cada vocal.
 * La clase CuentaCaracteres.java lee el carácter a contar por la línea de argumentos, por lo que habrá indicarle el carácter a buscar
 * en el momento de construir el proceso. La salida de todos los procesos debe ser la salida estándar, la heredada por el padre.
 *
 * Realiza dos ejecuciones, una en la que cada proceso espere al anterior y otra en la que los procesos no se esperen entre sí.
 * Cuenta la cantidad de milisegundos que transcurren entre los dos casos. ¿Hay alguna diferencia? Pon cada solución en dos clases distintas.
 */
public class MainEspera {
    public static void main(String[] args) {
        String[] comandoA = {"java", "src\\Ej3\\CuentaCaracteres.java", "a"};
        String[] comandoE = {"java", "src\\Ej3\\CuentaCaracteres.java", "e"};
        String[] comandoI = {"java", "src\\Ej3\\CuentaCaracteres.java", "i"};
        String[] comandoO = {"java", "src\\Ej3\\CuentaCaracteres.java", "o"};
        String[] comandoU = {"java", "src\\Ej3\\CuentaCaracteres.java", "u"};

        ProcessBuilder pbA = new ProcessBuilder(comandoA);
        ProcessBuilder pbE = new ProcessBuilder(comandoE);
        ProcessBuilder pbI = new ProcessBuilder(comandoI);
        ProcessBuilder pbO = new ProcessBuilder(comandoO);
        ProcessBuilder pbU = new ProcessBuilder(comandoU);

        pbA.inheritIO();
        pbE.inheritIO();
        pbI.inheritIO();
        pbO.inheritIO();
        pbU.inheritIO();

        try {
            long inicio = System.currentTimeMillis();       // Momento de inicio de la ejecución
            // Inicio los procesos y espero a que terminen para iniciar el siguiente
            Process processA = pbA.start();
            processA.waitFor();
            Process processE = pbE.start();
            processE.waitFor();
            Process processI = pbI.start();
            processI.waitFor();
            Process processO = pbO.start();
            processO.waitFor();
            Process processU = pbU.start();
            processU.waitFor();     // Espero a que termine de ejecutarse el último proceso

            long fin = System.currentTimeMillis();      // Momento de finalización de la ejecución

            System.out.println("Tiempo de ejecución: " + (fin-inicio) + " milisegundos");

        } catch (IOException e) {
            System.out.println("Error al iniciar algún proceso");
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error, se ha interrumpido la ejecución de algún proceso");
            System.out.println(e.getMessage());;
        }
    }
}
