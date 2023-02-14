package Ej2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    private static final int PORT = 50000;

    public static void main(String[] args) {
        try {
            // Dirección del servidor
            InetAddress direccion = InetAddress.getByName("127.0.0.1");     // InetAddress direccion = InetAddress.getLocalHost();
            String cadenaLeida;
            String respuesta;

            // Creación del socket
            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket();

            // Leemos la operación a realizar
            System.out.println("Ingrese una cadena ('CREATE código nombreAlumno' o 'SELECT')");
            cadenaLeida = leerString();

            // Enviamos el mensaje
            System.out.println("Inicio de envío de datos");
            enviarMensaje(socket, cadenaLeida, direccion, PORT);
            System.out.println("Fin de envío de datos");
            // Esperamos la respuesta
            System.out.println("Esperando respuesta...");
            respuesta = leerMensaje(socket);
            System.out.println("Respuesta del servidor:");
            System.out.println(respuesta);

            // Cerramos las conexiones
            System.out.println("Cierre de conexiones");
            socket.close();
        } catch (IOException e) {
            System.err.println("Error al enviar el paquete");
            throw new RuntimeException(e);
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

    /**
     * Envía un mensaje al socket pasado mediante su dirección y puerto de escucha.
     * Para ello crea un buffer de salida de bytes del mensaje, lo mete en un datagrama para enviarlo por el socket.
     * @param socket Socket del lado cliente
     * @param mensaje Mensaje que queremos enviar
     * @param address Dirección del servidor
     * @param port Puerto de escucha del servidor
     * @throws IOException En caso de que ocurra un error al enviar el datagrama
     */
    private static void enviarMensaje(DatagramSocket socket, String mensaje, InetAddress address, int port) throws IOException {
        byte[] bufferSalida = mensaje.getBytes();
        DatagramPacket packetSalida1 = new DatagramPacket(bufferSalida, bufferSalida.length, address, port);
        socket.send(packetSalida1);
    }

    /**
     * Queda a la espera de recibir un mensaje por el socket pasado.
     * Una vez lo recibe lo mete en un datagrama y lo transformamos en una cadena.
     * @param socket Socket del lado cliente
     * @return Mensaje recibido por el servidor
     * @throws IOException En caso de que ocurra un error al recibir el datagrama
     */
    private static String leerMensaje(DatagramSocket socket) throws IOException {
        String mensaje = "";
        byte[] bufferEntrada = new byte[64];

        DatagramPacket packetEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        socket.receive(packetEntrada);

        mensaje = new String(packetEntrada.getData());
        return mensaje.trim();
    }
}
