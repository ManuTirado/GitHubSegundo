package EjerciciosUDP.Ej1;

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

            // Pido el usuario
            System.out.println("Ingrese su usuario:");
            String nombre = leerString();

            // Creación del mensaje
            byte[] buffer = nombre.getBytes();

            System.out.println("Creamos el datagrama");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 41500);

            System.out.println("Enviamos el datagrama");
            socket.send(packet);

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
