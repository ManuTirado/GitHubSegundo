package Ej1;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class GestorProcesosTCP extends Thread {
    Socket socketCliente;

    public GestorProcesosTCP(Socket socketCliente) {
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

            // Intercambiar datos con el cliente
            // Leemos el mensaje del cliente
            System.out.println("(Servidor): Leo la operación");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String cadenaLeidaDelCliente = bufferedReader.readLine();
            System.out.println("(Servidor): Operación enviada por el cliente: '" + cadenaLeidaDelCliente + "'");

            // Enviamos la respuesta al cliente
            System.out.println("(Servidor): Envío resultado al cliente '" + socketCliente.getInetAddress() + "'");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            String respuesta = String.valueOf(realizarOperacion(cadenaLeidaDelCliente));
            bufferedWriter.write(respuesta);
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
            System.err.println("Error de entrada/salida en la comunicación con el cliente");
        }
    }

    /**
     * Realiza la operación correspondiente con los números pasados.
     * El formato debe ser 'num1;operacion,num2', si no devolverá 0.
     * @param cadena cadena que contiene la operación ('num1;operacion,num2')
     * @return Resultado de la operación o 0 si hubo algún error
     */
    private int realizarOperacion(String cadena) {
        int respuesta = 0;  // Por defecto es 0
        int num1, num2;
        String[] operacion = cadena.split(";");
        try {
            // En caso de que alguno de los números no sea un entero válido salta la excepción 'NumberFormatException'
            num1 = Integer.parseInt(operacion[0]);
            num2 = Integer.parseInt(operacion[2]);
            switch (operacion[1]) {     // Según el operador realiza la operación correspondiente y asigna el resultado a respuesta
                case "+" -> respuesta = num1 + num2;
                case "-" -> respuesta = num1 - num2;
                case "*" -> respuesta = num1 * num2;
                case "/" -> respuesta = num1 / num2;
                default -> throw new NumberFormatException();   //En caso de que el operador no coincida con ninguno lanzaremos esta excepción controlada
            }
        } catch (NumberFormatException e) { // En caso de que alguno de los números no sea correcto (contiene letras, etc)
            System.err.println("La operación '" + cadena + "' enviada por el cliente '" + socketCliente.getInetAddress() + "' no es válida");
        } catch (ArithmeticException e) {   // En caso de que alguna operación de error como x/0 recogeremos la exepción
            System.err.println("La operación '" + cadena + "' enviada por el cliente '" + socketCliente.getInetAddress() + "' es errónea");
        }
        return respuesta;
    }
}
