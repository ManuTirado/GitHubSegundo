package Ej2;

/***
 * Crea un programa que lance la clase ProcesoLento.Java
 * (para ello, mete en el mismo paquete la clase ProcesoLento.java y la clase que vayas a crear).
 * Utiliza el método isAlive() de la clase Process para comprobar si la clase ProcesoLento se sigue ejecutando e imprimir
 * un mensaje indicando que el proceso sigue en ejecución. Esta comprobación debe hacerla cada 3 segundos mientras esté en ejecución.
 * Cuando ya no esté en ejecución debe terminar, para lo cual utiliza Thread.sleep(int tiempo_ms).
 */
public class Main {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("java","src\\Ej2\\ProcesoLento.java");
        processBuilder.inheritIO();
        try {
            Process process = processBuilder.start();

            while (process.isAlive()) {
                System.out.println("¡¡ Está vivooooo !!");
                Thread.sleep(3000);
            }
            System.out.println("C murió ;(");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}