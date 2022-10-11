package Ej8;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        File salida = new File("src\\Ej8\\salidaProcesoLento2.txt");
        String[] comando = {"java", "src\\Ej2\\ProcesoLento.java"};

        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;

        try {
            if (!salida.exists()) {
                salida.createNewFile();
            }

            Process process = Runtime.getRuntime().exec(comando);


            while (process.isAlive()) {
                System.out.println("¡¡ Está vivooooo !!");
                Thread.sleep(3000);
            }
            System.out.println("C murió ;(");


            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            bufferedWriter = new BufferedWriter(new FileWriter(salida));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line + "\n");
            }
            bufferedReader.close();
            bufferedWriter.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
