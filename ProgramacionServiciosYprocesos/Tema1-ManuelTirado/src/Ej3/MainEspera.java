package Ej3;

import java.io.IOException;

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
