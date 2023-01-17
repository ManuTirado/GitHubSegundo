package Ejercicios.Ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class servidorTCP {
    public static void main(String[] args) {
        try {
            //Creación del socket servidor
            System.out.println("(Servidor): Abrinedo conexión");
            ServerSocket socketServidor = new ServerSocket(2500);
            while (true) {
                //Espera de la aceptación
                System.out.println("(Servidor): Esperando peticiones");
                Socket socketCliente = socketServidor.accept();

                //Flujos de entrada y de salida
                System.out.println("(Servidor): Abriendo flujos de entrada y de salida");
                InputStream inputStream = socketCliente.getInputStream();
                OutputStream outputStream = socketCliente.getOutputStream();

                //Intercambiar datos con el cliente
                System.out.println("(Servidor): Leo número del cliente");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                System.out.println("Número del cliente: " + line);

                System.out.println("(Servidor): Envío resultado al cliente");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String mensaje = esPrimo(Integer.parseInt(line));
                System.out.println("Resultado = " + mensaje);
                bufferedWriter.write(mensaje);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                //Cerrar flujos de lectura y escritura
                inputStream.close();
                outputStream.close();
                inputStreamReader.close();
                bufferedReader.close();
                outputStreamWriter.close();
                bufferedWriter.close();
            }
            //Cerrar la conexión
            /*
            System.out.println("Cerramos la conexión");
            socketServidor.close();
            socketCliente.close();

             */
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        ;
    }

    private static String esPrimo(int num) {
        boolean enc = false;
        String result = "Es primo";

        if (num <= 1){
            result = "No es primo";
        }
        for (int i = 2; i <= num / 2 && !enc ; i++) {
            if (num % i == 0){
                result = "No es primo";
                enc = true;
            }
        }

        return result;
    }
}
