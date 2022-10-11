package Ej7;

import java.util.Scanner;

public class FiltrarIPs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ip;
        String[] ipSeparada;

        for (int i = 0; i < 10; i++) {
            ip = sc.nextLine();
            ipSeparada = ip.split("\\.");
            if (Integer.parseInt(ipSeparada[0]) >= 0 && Integer.parseInt(ipSeparada[0]) <= 223) {
                System.out.println(ip);
            }
        }
    }
}
