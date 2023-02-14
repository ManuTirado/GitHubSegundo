package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.example.EntidadesHibernate.Factura;
import org.example.EntidadesHibernate.Mesa;
import org.example.EntidadesHibernate.Pedido;
import org.example.EntidadesHibernate.Productos;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    private static final DAL_Mongo dal = new DAL_Mongo();
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
    /**
     * Ejecuta el método de inserción adecuado según la tabla seleccionada
     * @param tablaSeleccionada, un entero que hace referencia a la tabla con el mismo número en el menú
     */
    private static void insertar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> insertarMesa();
            case 2 -> insertarFactura();
            case 3 -> insertarPedido();
            case 4 -> insertarProducto();
        }
    }

    /**
     * Ejecuta el método de listar adecuado según la tabla seleccionada
     * @param tablaSeleccionada, un entero que hace referencia a la tabla con el mismo número en el menú
     */
    private static void listar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> listarMesas();
            case 2 -> listarFacturas();
            case 3 -> listarPedidos();
            case 4 -> listarProductos();
        }
    }

    /**
     * Ejecuta el método de modificación adecuado según la tabla seleccionada
     * @param tablaSeleccionada, un entero que hace referencia a la tabla con el mismo número en el menú
     */
    private static void modificar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> modificarMesa();
            case 2 -> modificarFactura();
            case 3 -> modificarPedido();
            case 4 -> modificarProducto();
        }
    }

    /**
     * Ejecuta el método de borrado adecuado según la tabla seleccionada
     * @param tablaSeleccionada, un entero que hace referencia a la tabla con el mismo número en el menú
     */
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
    /**
     * Lee por consola los campos de una mesa y la inserta en la BBDD
     */
    private static void insertarMesa() {
        dal.insertar(new Mesa(5,2,3));
        /*
        int numComensales, reserva;
        System.out.println("Introduzca el número de comensales");
        numComensales = Utilidades.validarEntero();
        System.out.println("Introduzca el número de la reserva");
        reserva = Utilidades.validarEntero();
        dal.insertar(new Mesa(numComensales, reserva));
         */
    }

    /**
     * Lee por consola los campos de una factura y la inserta en la BBDD
     */
    private static void insertarFactura() {
        /*
        int idMesa;
        String tipoPago;
        BigDecimal importe;

        System.out.println("Introduzca el id de la mesa");
        idMesa = Utilidades.validarEntero();
        Mesa mesa = dal.leer(idMesa, Mesa.class);

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
        dal.insertar(factura);
         */
    }

    /**
     * Lee por consola los campos de un pedido y la inserta en la BBDD
     */
    private static void insertarPedido() {
        /*
        int idFactura, idProducto;
        int cantidad;

        System.out.println("Introduzca el id de la factura");
        idFactura = Utilidades.validarEntero();
        Factura factura = dal.leer(idFactura, Factura.class);
        System.out.println("Introduzca el id del producto");
        idProducto = Utilidades.validarEntero();
        Productos producto = dal.leer(idProducto, Productos.class);

        System.out.println("Introduzca la cantidad");
        cantidad = Utilidades.validarEntero();

        Pedido pedido = new Pedido(factura, producto, cantidad);
        dal.insertar(pedido);
        */
    }

    /**
     * Lee por consola los campos de un producto y la inserta en la BBDD
     */
    private static void insertarProducto() {
        String Denominacion;
        BigDecimal Precio;
        System.out.println("Introduzca la denominación");
        Denominacion = Utilidades.leerString();
        System.out.println("Introduzca el precio");
        Precio = BigDecimal.valueOf(Utilidades.validarFloat());
        dal.insertar(new Productos(Denominacion, Precio));
    }
    //endregion

    //region Métodos Listar
    /**
     * Muestra por pantalla todos los registros de la tabla Mesa
     */
    private static void listarMesas() {
        ArrayList<Mesa> mesas = dal.leerTodosRegistros(Mesa.class);
        System.out.println("/  /  /  /  / MESAS /  /  /  /  /");
        for (Mesa m :
                mesas) {
            System.out.println(m.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /");
    }

    /**
     * Muestra por pantalla todos los registros de la tabla Factura
     */
    private static void listarFacturas() {
        ArrayList<Factura> facturas = dal.leerTodosRegistros(Factura.class);
        System.out.println("/  /  /  /  / FACTURAS /  /  /  /  /");
        for (Factura f :
                facturas) {
            System.out.println(f.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /");
    }

    /**
     * Muestra por pantalla todos los registros de la tabla Pedido
     */
    private static void listarPedidos() {
        ArrayList<Pedido> pedidos = dal.leerTodosRegistros(Pedido.class);
        System.out.println("/  /  /  /  / PEDIDOS /  /  /  /  /");
        for (Pedido p :
                pedidos) {
            System.out.println(p.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /");
    }

    /**
     * Muestra por pantalla todos los registros de la tabla Productos
     */
    private static void listarProductos() {
        ArrayList<Productos> productos = dal.leerTodosRegistros(Productos.class);
        System.out.println("/  /  /  /  / PRODUCTOS /  /  /  /  /");
        for (Productos p :
                productos) {
            System.out.println(p.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /");
    }
    //endregion

    //region Métodos Modificar
    /**
     * Lee por consola el id de la mesa pedida, si existe lee los campos nuevos por consola y lo inserta en la BBDD
     */
    private static void modificarMesa() {
        System.out.println("Introduzca el id de la mesa a modificar");
        Mesa mesa = dal.leer(Utilidades.validarEntero(), Mesa.class);
        if (mesa != null) {
            System.out.println("==> " + mesa);
            System.out.println("Introduzca el número de comensales");
            mesa.setNumComensales(Utilidades.validarEntero());
            System.out.println("Introduzca el número de la reserva");
            mesa.setReserva(Utilidades.validarEntero());
            dal.insertar(mesa);
        } else {
            System.out.println("EL id introducido no existe");
        }
    }

    /**
     * Lee por consola el id de la factura pedida, si existe lee los campos nuevos por consola y lo inserta en la BBDD
     */
    private static void modificarFactura() {
        /*
        System.out.println("Introduzca el id de la factura a modificar");
        Factura factura = dal.leer(Utilidades.validarEntero(), Factura.class);
        if (factura != null) {
            System.out.println("==> " + factura);
            System.out.println("Introduzca el id de la mesa");
            Mesa mesa = dal.leer(Utilidades.validarEntero(), Mesa.class);
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
            dal.insertar(factura);
        } else {
            System.out.println("EL id introducido no existe");
        }
         */
    }

    /**
     * Lee por consola el id del pedido, si existe lee los campos nuevos por consola y lo inserta en la BBDD
     */
    private static void modificarPedido() {
        /*
        System.out.println("Introduzca el id del pedido a modificar");
        Pedido pedido = dal.leer(Utilidades.validarEntero(), Pedido.class);
        if (pedido != null) {
            System.out.println("==> " + pedido);
            System.out.println("Introduzca el id de la factura");
            Factura factura = dal.leer(Utilidades.validarEntero(), Factura.class);
            pedido.setFactura(factura);
            System.out.println("Introduzca el id del producto");
            Productos producto = dal.leer(Utilidades.validarEntero(), Productos.class);
            pedido.setProducto(producto);
            System.out.println("Introduzca la cantidad");
            pedido.setCantidad(Utilidades.validarEntero());
            dal.insertar(pedido);
        } else {
            System.out.println("EL id introducido no existe");
        }
         */
    }

    /**
     * Lee por consola el id del producto pedido, si existe lee los campos nuevos por consola y lo inserta en la BBDD
     */
    private static void modificarProducto() {
        System.out.println("Introduzca el id del producto a modificar");
        Productos producto = dal.leer(Utilidades.validarEntero(), Productos.class);
        if (producto != null) {
            System.out.println("==> " + producto);
            System.out.println("Introduzca la denominación");
            producto.setDenominacion(Utilidades.leerString());
            System.out.println("Introduzca el precio");
            producto.setPrecio(BigDecimal.valueOf(Utilidades.validarFloat()));
            dal.insertar(producto);
        } else {
            System.out.println("EL id introducido no existe");
        }
    }
    //endregion

    //region Métodos Borrar
    /**
     * Lee por consola el id de la mesa a borrar e intenta borrarla
     */
    private static void borrarMesa() {
        System.out.println("Introduzca el id de la mesa a borrar");
        Mesa mesa = dal.leer(Utilidades.validarEntero(), Mesa.class);
        dal.borrar(mesa);
    }

    /**
     * Lee por consola el id de la factura a borrar e intenta borrarla
     */
    private static void borrarFactura() {
        System.out.println("Introduzca el id de la factura a borrar");
        Factura factura = dal.leer(Utilidades.validarEntero(), Factura.class);
        dal.borrar(factura);
    }

    /**
     * Lee por consola el id del pedido a borrar e intenta borrarlo
     */
    private static void borrarPedido() {
        System.out.println("Introduzca el id del pedido a borrar");
        Pedido pedido = dal.leer(Utilidades.validarEntero(), Pedido.class);
        dal.borrar(pedido);
    }

    /**
     * Lee por consola el id del producto a borrar e intenta borrarlo
     */
    private static void borrarProducto() {
        System.out.println("Introduzca el id del producto a borrar");
        Productos producto = dal.leer(Utilidades.validarEntero(), Productos.class);
        dal.borrar(producto);
    }
    //endregion
}