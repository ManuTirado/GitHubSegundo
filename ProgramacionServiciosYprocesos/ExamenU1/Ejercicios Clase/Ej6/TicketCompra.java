package Ej6;

import java.util.Scanner;

public class TicketCompra {
    public static void main(String[] args) {
        int codigo,cantidad;
        float precio, total;

        Scanner sc = new Scanner(System.in);

        codigo = sc.nextInt();
        precio = sc.nextFloat();
        cantidad = sc.nextInt();
        sc.close();

        total = precio * cantidad;
        System.out.printf("%d - %.2f - %d - %.2f\n", codigo, precio, cantidad, total);
    }
}
