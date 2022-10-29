package Ej3;

import java.io.IOException;

public class MainConcurrente {
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

        // Especifico la entrada/salida estándar para los procesos
        pbA.inheritIO();
        pbE.inheritIO();
        pbI.inheritIO();
        pbO.inheritIO();
        pbU.inheritIO();

        try {
            long inicio = System.currentTimeMillis();   // Momento de inicio de la ejecución
            // Inicio los procesos
            Process processA = pbA.start();
            Process processE = pbE.start();
            Process processI = pbI.start();
            Process processO = pbO.start();
            Process processU = pbU.start();
            // No sigue la ejecución hasta que terminen todos los procesos
            while (processA.isAlive() || processE.isAlive() || processI.isAlive() || processO.isAlive() || processU.isAlive()) {}

            long fin = System.currentTimeMillis(); // Momento de finalización de la ejecución

            System.out.println("Tiempo de ejecución: " + (fin-inicio) + " milisegundos");

        } catch (IOException e) {
            System.out.println("Error al iniciar algún proceso");
            System.out.println(e.getMessage());
        }
    }
}
