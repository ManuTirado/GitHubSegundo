import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class servidorTCP {
    public static void main(String[] args) {
        try {
            //Creación del socket servidor
            System.out.println("(Servidor): Abriendo conexión");
            ServerSocket socketServidor = new ServerSocket(3100);
            while (true) {
                //Espera de la aceptación
                System.out.println("(Servidor): Esperando peticiones");
                Socket socketCliente = socketServidor.accept();

                //Flujos de entrada y de salida
                System.out.println("(Servidor): Abriendo flujos de entrada y de salida");
                InputStream inputStream = socketCliente.getInputStream();
                OutputStream outputStream = socketCliente.getOutputStream();

                //Intercambiar datos con el cliente
                System.out.println("(Servidor): Leo ruta del cliente");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String rutaLeida = bufferedReader.readLine();
                System.out.println("Ruta enviada por el cliente: '" + rutaLeida + "'");

                System.out.println("(Servidor): Envío resultado al cliente");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String mensaje = leerArchivo(rutaLeida);
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
    }

    private static String leerArchivo(String rutaLeida) {
        String salida = "";
        String ansiRed = "\u001B[31m", ansiBlue = "\u001B[34m", ansiReset = "\u001B[0m", ansiGreen = "\u001B[32m", ansiYellow = "\u001B[33m";
        File fichero = new File(rutaLeida);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            String line = br.readLine();
            while (line != null) {
                salida += ansiBlue + line + ansiReset + "\n";
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            salida = ansiRed + "Error, archivo no encontrado" + ansiReset;
        } catch (IOException e) {
            salida = ansiRed + "Error de entrada/salida al leer el archivo" + ansiReset;
        }
        return salida;
    }
}
