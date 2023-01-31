package PruebasTCP.ProtocoloTCPejercicio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class servidorTCP {
    public static void main(String[] args) {
        try {
            //Creación del socket servidor
            System.out.println("(Servidor): Abrinedo conexión");
            ServerSocket socketServidor = new ServerSocket(49900);
            while (true) {
                //Espera de la aceptación
                System.out.println("(Servidor): Esperando peticiones");
                Socket socketCliente = socketServidor.accept();

                //Flujos de entrada y de salida
                System.out.println("(Servidor): Abriendo flujos de entrada y de salida");
                InputStream inputStream = socketCliente.getInputStream();
                OutputStream outputStream = socketCliente.getOutputStream();

                //Intercambiar datos con el cliente
                System.out.println("(Servidor): Leo mensaje del cliente");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                System.out.println("Mensaje del cliente: " + bufferedReader.readLine());

                System.out.println("(Servidor): Envío mensaje de texto al cliente");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                bufferedWriter.write("Hola soy el lolo servidor");
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
}
