package EjerciciosTCP.Ejercicio4;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class clienteTCP {

    private static final String RUTA_FICHERO = "src\\EjerciciosTCP\\Ejercicio4\\numeros.txt";

    public static void main(String[] args) {
        try {
            File file = new File(RUTA_FICHERO);
            BufferedReader lectorFichero = new BufferedReader(new FileReader(file));

            //Dirección de socket tipo cliente
            //Dirección ip del servidor y puerto por el que escucha
            System.out.println("(Cliente): Creación de socket");
            InetAddress direccion = InetAddress.getLocalHost();
            Socket socketCliente = new Socket(direccion, 3000);

            //Abrir flujo de lectura y escritura
            System.out.println("(Cliente): Apertura de flujos de entrada y salida");
            InputStream inputStream = socketCliente.getInputStream();
            OutputStream outputStream = socketCliente.getOutputStream();

            //Intercambio de datos con el servidor
            //Envío de texto al servidor
            System.out.println("(Cliente) Envía el número al servidor");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            String line = lectorFichero.readLine();
            while (line != null) {
                int num = Integer.parseInt(line);
                System.out.println("Número leido: " + num);

                bufferedWriter.write(String.valueOf(num));
                bufferedWriter.newLine();
                bufferedWriter.flush();

                line = lectorFichero.readLine();
            }
            bufferedWriter.close();
            outputStreamWriter.close();

            //Leemos la respuesta del servidor
            System.out.println("(Cliente): Lee la respuesta del servidor");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("Mensaje enviado por el servidor: " + bufferedReader.readLine());

            //Cerrar los flujos de escritura y de lectura
            System.out.println("(Cliente): Cerramos flujo de lectura y escritura");
            outputStream.close();
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();

            //Cerramos la conexión
            System.out.println("Se cierra la conexión con el servidor");
            socketCliente.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    private static String leerNumero() {
        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.print("==> ");
            num = sc.nextLine();
            if (Integer.parseInt(num) < 0) {
                System.out.println("El número no puede ser negativo, inténtelo de nuevo...");
            }
        } while (Integer.parseInt(num) < 0);
        return num;
    }
}
