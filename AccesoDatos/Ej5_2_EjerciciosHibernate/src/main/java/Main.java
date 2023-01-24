import EntidadesHibernate.Factura;
import EntidadesHibernate.Mesa;
import EntidadesHibernate.Pedido;
import EntidadesHibernate.Productos;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    private static final DAL_Hibernate dal = new DAL_Hibernate();

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
                    case 2 -> listar(opc_menu_principal);
                    case 3 -> modificar(opc_menu_principal);
                    case 4 -> borrar(opc_menu_principal);
                }
            }
        } while (opc_menu_principal != 0);
    }

    //region Métodos menú
    private static void insertar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> insertarMesa();
            case 2 -> insertarFactura();
            case 3 -> insertarPedido();
            case 4 -> insertarProducto();
        }
    }

    private static void listar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> listarMesas();
            case 2 -> listarFacturas();
            case 3 -> listarPedidos();
            case 4 -> listarProductos();
        }
    }

    private static void modificar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> modificarMesa();
            case 2 -> modificarFactura();
            case 3 -> modificarPedido();
            case 4 -> modificarProducto();
        }
    }

    private static void borrar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> borrarMesa();
            case 2 -> borrarFactura();
            case 3 -> borrarPedido();
            case 4 -> borrarProducto();
        }
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
        idMesa = Utilidades.validarEntero();
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
        Factura factura = new Factura(mesa, tipoPago, importe);
        dal.insertarFactura(factura);
    }

    private static void insertarPedido() {
        int idFactura, idProducto;
        int cantidad;

        System.out.println("Introduzca el id de la factura");
        idFactura = Utilidades.validarEntero();
        Factura factura = dal.leerFactura(idFactura);
        System.out.println("Introduzca el id del producto");
        idProducto = Utilidades.validarEntero();
        Productos producto = dal.leerProducto(idProducto);

        System.out.println("Introduzca la cantidad");
        cantidad = Utilidades.validarEntero();

        Pedido pedido = new Pedido(factura, producto, cantidad);
        dal.insertarPedido(pedido);
    }

    private static void insertarProducto() {
        String Denominacion;
        BigDecimal Precio;
        System.out.println("Introduzca la denominación");
        Denominacion = Utilidades.leerString();
        System.out.println("Introduzca el precio");
        Precio = BigDecimal.valueOf(Utilidades.validarFloat());
        dal.insertarProducto(new Productos(Denominacion, Precio));
    }
    //endregion

    //region Métodos Listar
    private static void listarMesas() {
        ArrayList<Mesa> mesas = dal.leerTodasMesas();
        System.out.println("/  /  /  /  / MESAS /  /  /  /  /");
        for (Mesa m :
                mesas) {
            System.out.println(m.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /");
    }

    private static void listarFacturas() {
        ArrayList<Factura> facturas = dal.leerTodasFacturas();
        System.out.println("/  /  /  /  / FACTURAS /  /  /  /  /");
        for (Factura f :
                facturas) {
            System.out.println(f.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /");
    }

    private static void listarPedidos() {
        ArrayList<Pedido> pedidos = dal.leerTodosPedidos();
        System.out.println("/  /  /  /  / PEDIDOS /  /  /  /  /");
        for (Pedido p :
                pedidos) {
            System.out.println(p.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /");
    }

    private static void listarProductos() {
        ArrayList<Productos> productos = dal.leerTodosProductos();
        System.out.println("/  /  /  /  / PRODUCTOS /  /  /  /  /");
        for (Productos p :
                productos) {
            System.out.println(p.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /");
    }
    //endregion

    //region Métodos Modificar
    private static void modificarMesa() {
        System.out.println("Introduzca el id de la mesa a modificar");
        Mesa mesa = dal.leerMesa(Utilidades.validarEntero());
        if (mesa != null) {
            System.out.println("==> " + mesa);
            System.out.println("Introduzca el número de comensales");
            mesa.setNumComensales(Utilidades.validarEntero());
            System.out.println("Introduzca el número de la reserva");
            mesa.setReserva(Utilidades.validarEntero());
            dal.insertarMesa(mesa);
        } else {
            System.out.println("EL id introducido no existe");
        }
    }

    private static void modificarFactura() {
        System.out.println("Introduzca el id de la factura a modificar");
        Factura factura = dal.leerFactura(Utilidades.validarEntero());
        if (factura != null) {
            System.out.println("==> " + factura);
            System.out.println("Introduzca el id de la mesa");
            Mesa mesa = dal.leerMesa(Utilidades.validarEntero());
            factura.setMesa(mesa);
            System.out.println("Introduzca el importe");
            factura.setImporte(BigDecimal.valueOf(Utilidades.validarFloat()));
            System.out.println("Escoja el tipo de pago (1-'Efectivo' / 2-'Tarjeta')");
            int opc = Utilidades.validarEntero(1, 2);
            if (opc == 1) {
                factura.setTipoPago("efectivo");
            } else {
                factura.setTipoPago("tarjeta");
            }
            dal.insertarFactura(factura);
        } else {
            System.out.println("EL id introducido no existe");
        }
    }

    private static void modificarPedido() {
        System.out.println("Introduzca el id del pedido a modificar");
        Pedido pedido = dal.leerPedido(Utilidades.validarEntero());
        if (pedido != null) {
            System.out.println("==> " + pedido);
            System.out.println("Introduzca el id de la factura");
            Factura factura = dal.leerFactura(Utilidades.validarEntero());
            pedido.setFactura(factura);
            System.out.println("Introduzca el id del producto");
            Productos producto = dal.leerProducto(Utilidades.validarEntero());
            pedido.setProducto(producto);
            System.out.println("Introduzca la cantidad");
            pedido.setCantidad(Utilidades.validarEntero());
            dal.insertarPedido(pedido);
        } else {
            System.out.println("EL id introducido no existe");
        }
    }

    private static void modificarProducto() {
        System.out.println("Introduzca el id del producto a modificar");
        Productos producto = dal.leerProducto(Utilidades.validarEntero());
        if (producto != null) {
            System.out.println("==> " + producto);
            System.out.println("Introduzca la denominación");
            producto.setDenominacion(Utilidades.leerString());
            System.out.println("Introduzca el precio");
            producto.setPrecio(BigDecimal.valueOf(Utilidades.validarFloat()));
            dal.insertarProducto(producto);
        } else {
            System.out.println("EL id introducido no existe");
        }
    }
    //endregion

    //region Métodos Borrar
    private static void borrarMesa() {
        System.out.println("Introduzca el id de la mesa a borrar");
        Mesa mesa = dal.leerMesa(Utilidades.validarEntero());
        dal.borrarMesa(mesa);
    }

    private static void borrarFactura() {
        System.out.println("Introduzca el id de la factura a borrar");
        Factura factura = dal.leerFactura(Utilidades.validarEntero());
        dal.borrarFactura(factura);
    }

    private static void borrarPedido() {
        System.out.println("Introduzca el id del pedido a borrar");
        Pedido pedido = dal.leerPedido(Utilidades.validarEntero());
        dal.borrarPedido(pedido);
    }

    private static void borrarProducto() {
        System.out.println("Introduzca el id del producto a borrar");
        Productos producto = dal.leerProducto(Utilidades.validarEntero());
        dal.borrarProducto(producto);
    }
    //endregion
}
