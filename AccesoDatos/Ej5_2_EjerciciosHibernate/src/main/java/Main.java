import EntidadesHibernate.Factura;
import EntidadesHibernate.Mesa;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static DAL_Hibernate dal = new DAL_Hibernate();

    public static void main(String[] args) {

        int opc_menu_principal;
        String tablaSeleccionada = "";
        do {
            Utilidades.imprimirMenuPrincipal();
            opc_menu_principal = Utilidades.validarEntero(0, 4);
            switch (opc_menu_principal) {
                case 0 -> System.out.println("Adios! 👋");
                case 1 -> tablaSeleccionada = "Mesa";
                case 2 -> tablaSeleccionada = "Factura";
                case 3 -> tablaSeleccionada = "Pedido";
                case 4 -> tablaSeleccionada = "Productos";
            }
            int opc_menu_secundario = 1;
            while (opc_menu_secundario != 0 && opc_menu_principal != 0) {
                System.out.println("Tabla seleccionada => '" + tablaSeleccionada + "'");
                Utilidades.imprimirMenuSecundario();
                opc_menu_secundario = Utilidades.validarEntero(0, 4);
                switch (opc_menu_secundario) {
                    case 1 -> insertar(opc_menu_principal);
                    case 2 -> listar();
                    case 3 -> modificar();
                    case 4 -> borrar();
                }
            }
        } while (opc_menu_principal != 0);
    }

    //region Métodos menú
    private static void insertar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> insertarMesa();
            case 2 -> insertarFactura();
            /*
            case 3 -> ;
            case 4 -> ;
            */
        }
    }

    private static void listar() {

    }

    private static void modificar() {

    }

    private static void borrar() {
    }
    //endregion


    //region Métodos Insertar
    private static void insertarMesa() {
        int numComensales, reserva;
        System.out.println("Introduzca el número de comensales");
        numComensales = Utilidades.validarEntero();
        System.out.println("Introduzca el número de la reserva");
        reserva = Utilidades.validarEntero();
        dal.insertarMesa(new Mesa(numComensales, reserva));
    }

    private static void insertarFactura() {
        int idMesa;
        String tipoPago;
        BigDecimal importe;

        System.out.println("Introduzca el id de la mesa");
        idMesa=Utilidades.validarEntero();
        Mesa mesa = dal.leerMesa(idMesa);

        System.out.println("Introduzca el importe");
        importe = BigDecimal.valueOf(Utilidades.validarFloat());
        System.out.println("Escoja el tipo de pago (1-'Efectivo' / 2-'Tarjeta')");
        int opc = Utilidades.validarEntero(1, 2);
        if (opc == 1) {
            tipoPago = "efectivo";
        } else {
            tipoPago = "tarjeta";
        }
        Factura factura = new Factura(mesa,tipoPago,importe);
        dal.insertarFactura(factura);
    }


    //endregion
}
