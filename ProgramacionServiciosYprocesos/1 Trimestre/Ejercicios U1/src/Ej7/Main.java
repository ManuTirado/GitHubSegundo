package Ej7;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/***
 * En este ejercicio vamos a lanzar varios procesos, cuyas entradas y salidas están enlazadas mediante tuberías.
 * Para lo que usaremos el método startPipeline. Para ello, vamos a crear 3 clases distintas que serán los distintos procesos a lanzar:
 *      - Clase 1: Va a generar 10 direcciones IP de forma aleatoria y las va a imprimir por la salida estándar.
 *      - Clase 2: Va a leer 10 direcciones IP de teclado y va a imprimir por consola únicamente aquellas que pertenezcan a las clases A, B o C.
 *      - Clase 3: Va a leer una serie de direcciones IP por teclado (no sabemos cuántas van a llegar) y va a imprimir por consola la dirección IP
 *                 y a continuación la clase a la que pertenece.
 *
 * Tienes que crear una clase que lance estos tres procesos en el orden en el que se especifican,
 * de forma que la salida estándar de uno sea la entrada estándar del siguiente. La salida estándar del último proceso debe ser un fichero de texto.
 * CONSEJO: Establece para los tres procesos la salida de error estándar, para en caso de que haya algún error durante la ejecución, este se pinte en la consola.
 */
public class Main {
    public static void main(String[] args) {
        File salida = new File("src\\Ej7\\salida.txt");

        ProcessBuilder pb1 = new ProcessBuilder("java", "src\\Ej7\\GenerarDireccionesIP.java");
        ProcessBuilder pb2 = new ProcessBuilder("java", "src\\Ej7\\FiltrarIPs.java");
        ProcessBuilder pb3 = new ProcessBuilder("java", "src\\Ej7\\LeerDireccionesYmostrarlas.java");

        pb1.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb2.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb3.redirectError(ProcessBuilder.Redirect.INHERIT);

        pb3.redirectOutput(ProcessBuilder.Redirect.to(salida));

        List<ProcessBuilder> lpb = new ArrayList<>();
        lpb.add(pb1);
        lpb.add(pb2);
        lpb.add(pb3);

        try {
            ProcessBuilder.startPipeline(lpb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
