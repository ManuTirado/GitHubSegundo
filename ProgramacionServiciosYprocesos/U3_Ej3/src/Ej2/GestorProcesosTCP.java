package Ej2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class GestorProcesosTCP extends  Thread{
    Socket socketCliente;

    public  GestorProcesosTCP(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        realizarProceso();
    }

    private void realizarProceso() {
        try {
            System.out.println("Conectado al cliente con ip: " + socketCliente.getInetAddress());
            //Flujos de entrada y de salida
            System.out.println("(Servidor): Abriendo flujos de entrada y de salida");
            InputStream inputStream = socketCliente.getInputStream();
            OutputStream outputStream = socketCliente.getOutputStream();

            //Intercambiar datos con el cliente
            System.out.println("(Servidor): Leo la dirección web");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String direccionWebLeida = bufferedReader.readLine();
            System.out.println("(Servidor): Dirección web enviada por el cliente: '" + direccionWebLeida + "'");

            System.out.println("(Servidor): Envío resultado al cliente");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            String mensaje = buscarIpEnDNS(direccionWebLeida);
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
            System.out.println("(Servidor): Desconectando al cliente con ip: " + socketCliente.getInetAddress());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida en la comunicación con el cliente");;
        }
    }

    private static String buscarIpEnDNS(String direccionWeb) {
        String ruta_fichero = "src/Ej2/DNS.txt", salida = "";
        File fichero = new File(ruta_fichero);
        boolean encontrado = false;

        String ansiRed = "\u001B[31m", ansiBlue = "\u001B[34m", ansiReset = "\u001B[0m", ansiGreen = "\u001B[32m", ansiYellow = "\u001B[33m";

        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            String line = br.readLine();
            while (line != null && !encontrado) {
                if (line.split(":")[0].equals(direccionWeb)) {
                    salida = ansiBlue + line.split(":")[1] + ansiReset;
                    encontrado = true;
                }
                line = br.readLine();
            }
            if (!encontrado) {
                salida = ansiRed + "Dirección web no registrada" + ansiReset;
            }
        } catch (FileNotFoundException e) {
            salida = ansiRed + "Error, archivo no encontrado" + ansiReset;
        } catch (IOException e) {
            salida = ansiRed + "Error de entrada/salida al leer el archivo" + ansiReset;
        }
        return salida;
    }
}
