package Ej2;

import Ej1.GestorProcesosUDP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class servidorTCP {
    public static void main(String[] args) {
        try {
            //Creación del socket servidor
            System.out.println("(Servidor): Abriendo conexión");
            ServerSocket socketServidor = new ServerSocket(4000);
            while (true) {
                //Espera de la aceptación
                System.out.println("(Servidor): Esperando peticiones");
                Socket socketCliente = socketServidor.accept();

                new GestorProcesosTCP(socketCliente).start();
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
}
