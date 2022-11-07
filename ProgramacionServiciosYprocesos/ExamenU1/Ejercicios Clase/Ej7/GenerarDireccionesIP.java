package Ej7;

public class GenerarDireccionesIP {

    private static final int NUM_DIRECCIONES_IP = 10;

    public static void main(String[] args) {
        for (int i = 0; i < NUM_DIRECCIONES_IP; i++) {
            System.out.println(generarIpAleatoria());
        }

    }
    private static String generarIpAleatoria () {
        int num;
        StringBuilder sbIP = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            num = (int) (Math.random() * 256);
            if (i != 3) {
                sbIP.append(num).append(".");
            } else {
                sbIP.append(num);
            }
        }

        return sbIP.toString();
    }

}
