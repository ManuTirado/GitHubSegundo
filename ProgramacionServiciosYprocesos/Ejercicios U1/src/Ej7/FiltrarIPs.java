package Ej7;

import java.util.Scanner;

public class FiltrarIPs {
    public static void main(String[] args) {
        String ip;
        for (int i = 0; i < 10; i++) {
            ip = leerIP();
            String[] ipSeparada = ip.split("\\.");
            if (Integer.parseInt(ipSeparada[0])  >= 0 && Integer.parseInt(ipSeparada[0]) <= 223) {
                System.out.println(ip);
            }
        }
    }

    private static String leerIP () {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
