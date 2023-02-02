import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClienteUDP {

    public static void main(String[] args) {
        try {
            //Obtener dirección IP local
            InetAddress direccion = InetAddress.getLocalHost();
            int port = 50000;

            // Creación del socket
            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket();

            System.out.println("Inicio de envío de datos");
            for (int i = 0; i < 10000; i++) {
                enviarMensaje(socket, "Mensaje: " + i, direccion, port);
            }
            enviarMensaje(socket, "FIN", direccion, port);
            System.out.println("Fin de envío de datos");

            System.out.println("Cierre de conexiones");
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Error al obtener la dirección IP local");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error al enviar el paquete");
            throw new RuntimeException(e);
        }
    }

    private static void enviarMensaje(DatagramSocket socket, String mensaje, InetAddress address, int port) throws IOException {
        byte[] bufferSalida = mensaje.getBytes();
        DatagramPacket packetSalida1 = new DatagramPacket(bufferSalida, bufferSalida.length, address, port);
        socket.send(packetSalida1);
    }
}
