package EjerciciosUDP.Ej2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteUDP {

    public static void main(String[] args) {
        leerMensajeDelSevidor();
    }

    private static void leerMensajeDelSevidor() {
        try {
            //Obtener dirección IP local
            InetAddress direccion = InetAddress.getLocalHost();

            // Creación del socket
            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket();

            // Pido las cadenas
            System.out.println("Ingrese la cadena 1:");
            String cadena1 = leerString();
            System.out.println("Ingrese la cadena 2:");
            String cadena2 = leerString();

            // Creación de los mensajes
            byte[] buffer1 = cadena1.getBytes();
            byte[] buffer2 = cadena2.getBytes();

            System.out.println("Creamos los datagramas");
            DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length, direccion, 41500);
            DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length, direccion, 41500);

            System.out.println("Enviamos los datagramas");
            socket.send(packet1);
            socket.send(packet2);

            System.out.println("Mensajes enviados por el servidor:");
            System.out.println(leerMensaje(socket));
            System.out.println(leerMensaje(socket));

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

    private static String leerMensaje(DatagramSocket socket) throws IOException {
        String mensaje = "";
        byte[] bufferEntrada = new byte[64];

        DatagramPacket packetEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        socket.receive(packetEntrada);

        mensaje = new String(packetEntrada.getData());
        return mensaje.trim();
    }
}
