package org.example;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.EntidadesHibernate.Factura;
import org.example.EntidadesHibernate.Mesa;
import org.example.EntidadesHibernate.Pedido;
import org.example.EntidadesHibernate.Productos;

import java.math.BigDecimal;

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
     *
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
     *
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
     *
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
     *
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
        int id, numComensales, reserva;

        System.out.println("Introduzca el id de la mesa");
        id = Utilidades.validarEntero();
        if (dal.leer(id, Mesa.class) == null) {
            System.out.println("Introduzca el número de comensales");
            numComensales = Utilidades.validarEntero();
            System.out.println("Introduzca el número de la reserva");
            reserva = Utilidades.validarEntero();
            dal.insertar(new Mesa(id, numComensales, reserva));
        } else {
            System.out.println("Id de la mesa ya existente");
        }
    }

    /**
     * Lee por consola los campos de una factura y la inserta en la BBDD
     */
    private static void insertarFactura() {
        int idFactura, idMesa;
        String tipoPago;
        BigDecimal importe;

        System.out.println("Introduzca el id de la factura");
        idFactura = Utilidades.validarEntero();
        if (dal.leer(idFactura, Factura.class) == null) {
            System.out.println("Introduzca el id de la mesa");
            idMesa = Utilidades.validarEntero();
            Document mesa = dal.leer(idMesa, Mesa.class);

            if (mesa != null) {
                System.out.println("Introduzca el importe");
                importe = BigDecimal.valueOf(Utilidades.validarFloat());
                System.out.println("Escoja el tipo de pago (1-'Efectivo' / 2-'Tarjeta')");
                int opc = Utilidades.validarEntero(1, 2);
                if (opc == 1) {
                    tipoPago = "efectivo";
                } else {
                    tipoPago = "tarjeta";
                }
                Factura factura = new Factura(idFactura, idMesa, tipoPago, importe);
                dal.insertar(factura);
            } else {
                System.out.println("Mesa inexistente");
            }
        } else {
            System.out.println("Id de la Factura ya existente");
        }
    }

    /**
     * Lee por consola los campos de un pedido y la inserta en la BBDD
     */
    private static void insertarPedido() {
        int idPedido, idFactura, idProducto;
        int cantidad;

        System.out.println("Introduzca el id del pedido");
        idPedido = Utilidades.validarEntero();
        if (dal.leer(idPedido, Pedido.class) == null) {
            System.out.println("Introduzca el id de la factura");
            idFactura = Utilidades.validarEntero();
            Document factura = dal.leer(idFactura, Factura.class);
            if (factura != null) {
                System.out.println("Introduzca el id del producto");
                idProducto = Utilidades.validarEntero();
                Document producto = dal.leer(idProducto, Productos.class);
                if (producto != null) {
                    System.out.println("Introduzca la cantidad");
                    cantidad = Utilidades.validarEntero();

                    Pedido pedido = new Pedido(idPedido, idFactura, idProducto, cantidad);
                    dal.insertar(pedido);
                } else {
                    System.out.println("Producto inexistente");
                }
            } else {
                System.out.println("Factura inexistente");
            }
        } else {
            System.out.println("Id del Pedido ya existente");
        }
    }

    /**
     * Lee por consola los campos de un producto y la inserta en la BBDD
     */
    private static void insertarProducto() {
        int id;
        String Denominacion;
        BigDecimal Precio;
        System.out.println("Introduzca el id del producto");
        id = Utilidades.validarEntero();
        if (dal.leer(id, Productos.class) == null) {
            System.out.println("Introduzca la denominación");
            Denominacion = Utilidades.leerString();
            System.out.println("Introduzca el precio");
            Precio = BigDecimal.valueOf(Utilidades.validarFloat());
            dal.insertar(new Productos(id, Denominacion, Precio));
        } else {
            System.out.println("Id del Producto ya existente");
        }
    }
    //endregion

    //region Métodos Listar

    /**
     * Muestra por pantalla todos los registros de la tabla Mesa
     */
    private static void listarMesas() {
        MongoCollection<Document> docMesas = dal.leerTodosRegistros(Mesa.class);
        System.out.println("/  /  /  /  / MESAS /  /  /  /  /");
        for (Document m :
                docMesas.find()) {
            System.out.println(m.toJson());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /");
    }

    /**
     * Muestra por pantalla todos los registros de la tabla Factura
     */
    private static void listarFacturas() {
        MongoCollection<Document> docFacturas = dal.leerTodosRegistros(Factura.class);
        System.out.println("/  /  /  /  / FACTURAS /  /  /  /  /");
        for (Document f :
                docFacturas.find()) {
            System.out.println(f.toJson());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /");
    }

    /**
     * Muestra por pantalla todos los registros de la tabla Pedido
     */
    private static void listarPedidos() {
        MongoCollection<Document> docPedidos = dal.leerTodosRegistros(Pedido.class);
        System.out.println("/  /  /  /  / PEDIDOS /  /  /  /  /");
        for (Document p :
                docPedidos.find()) {
            System.out.println(p.toJson());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /");
    }

    /**
     * Muestra por pantalla todos los registros de la tabla Productos
     */
    private static void listarProductos() {
        MongoCollection<Document> docProductos = dal.leerTodosRegistros(Productos.class);
        System.out.println("/  /  /  /  / PRODUCTOS /  /  /  /  /");
        for (Document p :
                docProductos.find()) {
            System.out.println(p.toJson());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /");
    }
    //endregion

    //region Métodos Modificar

    /**
     * Lee por consola el ID de la mesa pedida, si existe lee los campos nuevos por consola y lo actualiza en la BBDD
     */
    private static void modificarMesa() {
        int idMesa, numComensales, reserva;

        System.out.println("Introduzca el id de la mesa a modificar");
        idMesa = Utilidades.validarEntero();
        Document antes = dal.leer(idMesa, Mesa.class);
        if (antes != null) {
            System.out.println("Introduzca el número de comensales");
            numComensales = Utilidades.validarEntero();
            System.out.println("Introduzca el número de la reserva");
            reserva = Utilidades.validarEntero();
            dal.actualizar(antes, new Mesa(idMesa, numComensales, reserva));
        } else {
            System.out.println("Id de la mesa inexistente");
        }
    }

    /**
     * Lee por consola el ID de la factura pedida, si existe lee los campos nuevos por consola y lo actualiza en la BBDD
     */
    private static void modificarFactura() {
        Document facturaAntes;
        int idFactura, idMesa;
        BigDecimal importe;
        String tipoPago;

        System.out.println("Introduzca el ID de la factura a modificar");
        idFactura = Utilidades.validarEntero();
        facturaAntes = dal.leer(idFactura, Factura.class);
        if (facturaAntes != null) {
            System.out.println("==> " + facturaAntes.toJson());
            System.out.println("Introduzca el ID de la mesa");
            idMesa = Utilidades.validarEntero();
            Document mesa = dal.leer(idMesa, Mesa.class);
            if (mesa != null) {
                System.out.println("Introduzca el importe");
                importe = BigDecimal.valueOf(Utilidades.validarFloat());
                System.out.println("Escoja el tipo de pago (1-'Efectivo' / 2-'Tarjeta')");
                int opc = Utilidades.validarEntero(1, 2);
                if (opc == 1) {
                    tipoPago = "efectivo";
                } else {
                    tipoPago = "tarjeta";
                }
                dal.actualizar(facturaAntes, new Factura(idFactura, idMesa, tipoPago, importe));
            } else {
                System.out.println("Id de la mesa inexistente");
            }
        } else {
            System.out.println("Id de la factura inexistente");
        }
    }

    /**
     * Lee por consola el ID del pedido, si existe lee los campos nuevos por consola y lo actualiza en la BBDD
     */
    private static void modificarPedido() {
        Document pedidoAntes;
        int idPedido, idFactura, idProducto, cantidad;

        System.out.println("Introduzca el ID del pedido a modificar");
        idPedido = Utilidades.validarEntero();
        pedidoAntes = dal.leer(idPedido, Pedido.class);
        if (pedidoAntes != null) {
            System.out.println("==> " + pedidoAntes.toJson());
            System.out.println("Introduzca el ID de la factura");
            idFactura = Utilidades.validarEntero();
            Document factura = dal.leer(idFactura, Factura.class);
            if (factura != null) {
                System.out.println("Introduzca el ID del producto");
                idProducto = Utilidades.validarEntero();
                Document producto = dal.leer(idProducto, Productos.class);
                if (producto != null) {
                    System.out.println("Introduzca la cantidad");
                    cantidad = Utilidades.validarEntero();
                    dal.actualizar(pedidoAntes, new Pedido(idPedido, idFactura, idProducto, cantidad));
                } else {
                    System.out.println("Id del producto inexistente");
                }
                System.out.println("Introduzca la cantidad");
                cantidad = Utilidades.validarEntero();
                dal.actualizar(pedidoAntes, new Pedido(idPedido, idFactura, idProducto, cantidad));
            } else {
                System.out.println("Id de la factura inexistente");
            }
        } else {
            System.out.println("Id del pedido inexistente");
        }
    }

    /**
     * Lee por consola el ID del producto pedido, si existe lee los campos nuevos por consola y lo actualiza en la BBDD
     */
    private static void modificarProducto() {
        int idProducto;
        String denominacion;
        BigDecimal precio;

        System.out.println("Introduzca el id del producto a modificar");
        idProducto = Utilidades.validarEntero();
        Document productoAntes = dal.leer(idProducto, Productos.class);
        if (productoAntes != null) {
            System.out.println("==> " + productoAntes.toJson());
            System.out.println("Introduzca la denominación");
            denominacion = Utilidades.leerString();
            System.out.println("Introduzca el precio");
            precio = BigDecimal.valueOf(Utilidades.validarFloat());
            dal.actualizar(productoAntes, new Productos(idProducto, denominacion, precio));
        } else {
            System.out.println("Id del producto inexistente");
        }
    }
    //endregion

    //region Métodos Borrar

    /**
     * Lee por consola el ID de la mesa a borrar e intenta borrarla.
     * Si existe, se borran las facturas y los pedidos asociados a ella además de la mesa
     */
    private static void borrarMesa() {
        Document mesa;
        FindIterable<Document> facturas, pedidos;
        int idMesa;

        System.out.println("Introduzca el id de la mesa a borrar (se borraran las facturas y los pedidos asociados a ella)");
        idMesa = Utilidades.validarEntero();
        mesa = dal.leer(idMesa, Mesa.class);
        if (mesa != null) {
            // obtener facturas de la mesa, obtener pedidos de cada factura y borrarlos
            facturas = dal.leerTodosRegistros(Factura.class, "idMesa", idMesa);
            for (Document factura : facturas) {  // obtener los pedidos de cada factura
                System.out.println("Borrando factura " + factura.toJson());
                pedidos = dal.leerTodosRegistros(Pedido.class, "idFactura", factura.getInteger("idFactura"));
                for (Document pedido : pedidos) {  // borrar los pedidos
                    System.out.println("    Borrando pedido " + pedido.toJson());
                    dal.borrar(pedido, Pedido.class);
                }
                dal.borrar(factura, Factura.class); // borrar la factura
            }
            System.out.println("        Borrando mesa " + mesa.toJson());
            System.out.println();
            dal.borrar(mesa, Mesa.class); // borrar la mesa
        } else {
            System.out.println("Mesa inexistente");
        }
    }

    /**
     * Lee por consola el ID de la factura a borrar e intenta borrarla.
     * Si existe, se borran los pedidos asociados a ella además de la factura
     */
    private static void borrarFactura() {
        Document factura;
        FindIterable<Document> pedidos;
        int idFactura;

        System.out.println("Introduzca el id de la factura a borrar (se borraran los pedidos asociados a ella)");
        idFactura = Utilidades.validarEntero();
        factura = dal.leer(idFactura, Factura.class);
        if (factura != null) {
            // obtener pedidos de la factura y borrarlos
            pedidos = dal.leerTodosRegistros(Pedido.class, "idFactura", idFactura);
            for (Document pedido : pedidos) {  // borrar los pedidos
                System.out.println("Borrando pedido " + pedido.toJson());
                dal.borrar(pedido, Pedido.class);
            }
            System.out.println("    Borrando factura " + factura.toJson());
            System.out.println();
            dal.borrar(factura, Factura.class); // borrar la factura
        } else {
            System.out.println("Factura inexistente");
        }
    }

    /**
     * Lee por consola el ID del pedido a borrar e intenta borrarlo.
     * Si existe, se borra el pedido
     */
    private static void borrarPedido() {
        Document pedido;
        int idPedido;

        System.out.println("Introduzca el id del pedido a borrar");
        idPedido = Utilidades.validarEntero();
        pedido = dal.leer(idPedido, Pedido.class);
        if (pedido != null) {
            System.out.println("Borrando pedido " + pedido.toJson());
            dal.borrar(pedido, Pedido.class); // borrar el pedido
        } else {
            System.out.println("Pedido inexistente");
        }
    }

    /**
     * Lee por consola el ID del producto a borrar e intenta borrarlo.
     * Si existe, se borran los pedidos asociados a él además del producto
     */
    private static void borrarProducto() {
        Document producto;
        FindIterable<Document> pedidos;
        int idProducto;

        System.out.println("Introduzca el id del producto a borrar (se borraran los pedidos asociados a él)");
        idProducto = Utilidades.validarEntero();
        producto = dal.leer(idProducto, Productos.class);
        if (producto != null) {
            // obtener pedidos del producto y borrarlos
            pedidos = dal.leerTodosRegistros(Pedido.class, "idProducto", idProducto);
            for (Document pedido : pedidos) {  // borrar los pedidos
                System.out.println("Borrando pedido " + pedido.toJson());
                dal.borrar(pedido, Pedido.class);
            }
            System.out.println("    Borrando producto " + producto.toJson());
            dal.borrar(producto, Productos.class); // borrar el producto
        } else {
            System.out.println("Producto inexistente");
        }
    }
    //endregion
}