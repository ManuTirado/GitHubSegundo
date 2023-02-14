package Ej1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class clienteTCP {

    private static final int PORT = 4000;   // Puerto de escucha del servidor

    public static void main(String[] args) {
        try {
            System.out.println("(Cliente): Creación del socket");
            InetAddress direccion = InetAddress.getByName("127.0.0.1");     //InetAddress direccion = InetAddress.getLocalHost();
            Socket socketCliente = new Socket(direccion, PORT);    //Dirección ip del servidor y puerto por el que escucha

            // Leemos la operación
            System.out.println("(Cliente) Ingrese la operación ('num1;operación;num2')");
            String operacionLeidaPorConsola = leerString();

            //Abrir flujo de lectura y escritura
            System.out.println("(Cliente): Apertura de flujos de entrada y salida");
            InputStream inputStream = socketCliente.getInputStream();
            OutputStream outputStream = socketCliente.getOutputStream();

            //Intercambio de datos con el servidor
            System.out.println("(Cliente) Envía la operación al servidor");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            //Envío la operación al servidor
            bufferedWriter.write(operacionLeidaPorConsola);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            //Leemos la respuesta del servidor
            System.out.println("(Cliente): Lee la respuesta del servidor");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("Mensaje enviado por el servidor: ");
            String respuestaServidor = bufferedReader.readLine();
            System.out.println(respuestaServidor);

            //Cerrar los flujos de escritura y de lectura
            System.out.println("(Cliente): Cerramos flujo de lectura y escritura");
            outputStream.close();
            inputStream.close();
            outputStreamWriter.close();
            bufferedWriter.close();
            inputStreamReader.close();
            bufferedReader.close();

            //Cerramos la conexión
            System.out.println("Se cierra la conexión con el servidor");
            socketCliente.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Lee un String por consola, hasta en retorno de carro
     * @return String leido
     */
    private static String leerString() {
        Scanner sc = new Scanner(System.in);
        System.out.print("==> ");
        return sc.nextLine();
    }
}
