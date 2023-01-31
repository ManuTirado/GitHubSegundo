package EjerciciosUDP.Ej3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteUDP {

    public static void main(String[] args) {
        try {
            //Obtener dirección IP local
            InetAddress direccion = InetAddress.getLocalHost();

            // Creación del socket
            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket();

            // Creación del mensaje
            System.out.println("Ingrese una cadena:");
            String mensaje = leerString();
            byte[] buffer = mensaje.getBytes();

            System.out.println("Creamos el datagrama");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 41500);

            System.out.println("Enviamos el datagrama");
            socket.send(packet);

            System.out.println("Creación del array de bytes");
            byte[] bufferEntrada = new byte[64];

            System.out.println("Creación del datagrama");
            DatagramPacket packetEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            System.out.println("A la espera de recibir el datagrama");
            socket.receive(packetEntrada);

            System.out.println("Leemos el mensaje");
            mensaje = new String(packetEntrada.getData());
            System.out.println("Mensaje enviado por el servidor: " + mensaje.trim());

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

    private static String leerString(){
        Scanner sc = new Scanner(System.in);
        System.out.print("==> ");
        return sc.nextLine();
    }
}
