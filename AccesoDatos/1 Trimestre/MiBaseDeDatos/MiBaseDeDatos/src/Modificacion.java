import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Modificacion {
    /***
     * Procedimiento que permite modificar un registro de la tabla Mesa, escogiéndolo por su id
     * @param st Statement
     */
    public static void modificarMesa(Statement st) {
        int mesaSeleccionada, numComensales, reserva;
        String sql;

        try {
            sql = "SELECT * FROM ad2223_mtirado.Mesa ORDER BY idMesa ASC LIMIT 1";
            imprimirMesa(st, sql);

            System.out.println("...");

            sql = "SELECT * FROM ad2223_mtirado.Mesa ORDER BY idMesa DESC LIMIT 1";
            imprimirMesa(st, sql);


            System.out.println();
            System.out.println("Seleccione el id de la Mesa que desea modificar:");
            mesaSeleccionada = Utilidades.validarEntero();

            sql = "SELECT * FROM ad2223_mtirado.Mesa WHERE idMesa=" + mesaSeleccionada;
            imprimirMesa(st, sql);

            System.out.println("Modificación:");
            System.out.print("NumComensales ");
            numComensales = Utilidades.validarEntero();
            System.out.print("Reserva ");
            reserva = Utilidades.validarEntero();

            sql = "UPDATE ad2223_mtirado.Mesa SET numComensales =" + numComensales + ", reserva=" + reserva + " WHERE idMesa =" + mesaSeleccionada + ";";
            st.executeUpdate(sql);
            sql = "SELECT * FROM ad2223_mtirado.Mesa WHERE idMesa=" + mesaSeleccionada;
            imprimirMesa(st, sql);

        } catch (SQLException e) {
            System.out.println("Error en el SQL");
        }
    }

    /***
     * Imprime 1 registro de Mesa de la consulta pasado por parámetro
     * @param st Statement
     * @param sql Sentencia sql de la que queremos imprimir el primer resultado
     */
    private static void imprimirMesa(Statement st, String sql) {
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("IdMesa:" + rs.getInt("idMesa") + ", numComensales:" + rs.getInt("numComensales") + ", reserva:" + rs.getInt("reserva"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Procedimiento que permite modificar un registro de la tabla Facturas, escogiéndolo por su id
     * @param st Statement
     */
    public static void modificarFactura(Statement st) {
        int facturaSeleccionada, idMesa, tipoPago;
        float importe;
        String tipoPagoStr;
        String sql;

        try {
            sql = "SELECT * FROM ad2223_mtirado.Factura ORDER BY idFactura ASC LIMIT 1";
            imprimirFactura(st, sql);

            System.out.println("...");

            sql = "SELECT * FROM ad2223_mtirado.Factura ORDER BY idFactura DESC LIMIT 1";
            imprimirFactura(st, sql);


            System.out.println();
            System.out.println("Seleccione el id de la Mesa que desea modificar:");
            facturaSeleccionada = Utilidades.validarEntero();

            sql = "SELECT * FROM ad2223_mtirado.Factura WHERE idFactura=" + facturaSeleccionada;
            imprimirFactura(st, sql);

            System.out.println("Modificación:");
            System.out.print("IdMesa ");
            idMesa = Utilidades.validarEntero();
            System.out.print("TipoPago (1.Efectivo - 2.Tarjeta) ");
            tipoPago = Utilidades.validarEntero(1, 2);
            if (tipoPago == 1) {
                tipoPagoStr = "efectivo";
            } else {
                tipoPagoStr = "tarjeta";
            }
            System.out.print("Importe ");
            importe = Utilidades.validarFloat();

            sql = "UPDATE ad2223_mtirado.Factura SET idMesa =" + idMesa + ", tipoPago='" + tipoPagoStr + "', importe=" + importe + " WHERE idFactura =" + facturaSeleccionada + ";";
            st.executeUpdate(sql);
            sql = "SELECT * FROM ad2223_mtirado.Factura WHERE idFactura=" + facturaSeleccionada;
            imprimirFactura(st, sql);
        } catch (SQLException e) {
            System.out.println("Error en el SQL");
        }

    }

    /***
     * Imprime 1 registro de Factura de la consulta pasado por parámetro
     * @param st Statement
     * @param sql Sentencia sql de la que queremos imprimir el primer resultado
     */
    private static void imprimirFactura(Statement st, String sql) {
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("idFactura:" + rs.getInt("idFactura") + ", idMesa:" + rs.getInt("idMesa") + ", tipoPago:" + rs.getString("tipoPago") + ", importe:" + rs.getFloat("importe"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Procedimiento que permite modificar un registro de la tabla Pedido, escogiéndolo por su id
     * @param st Statement
     */
    public static void modificarPedido(Statement st) {
        int idPedido, idFactura, idProducto, cantidad;
        String sql;

        try {
            sql = "SELECT * FROM ad2223_mtirado.Pedido ORDER BY idPedido ASC LIMIT 1";
            imprimirPedido(st, sql);

            System.out.println("...");

            sql = "SELECT * FROM ad2223_mtirado.Pedido ORDER BY idPedido DESC LIMIT 1";
            imprimirPedido(st, sql);


            System.out.println();
            System.out.println("Seleccione el id del pedido que desea modificar:");
            idPedido = Utilidades.validarEntero();

            sql = "SELECT * FROM ad2223_mtirado.Pedido WHERE idPedido=" + idPedido;
            imprimirPedido(st, sql);

            System.out.println("Modificación:");
            System.out.print("idFactura ");
            idFactura = Utilidades.validarEntero();
            System.out.print("idProducto ");
            idProducto = Utilidades.validarEntero();
            System.out.print("Cantidad ");
            cantidad = Utilidades.validarEntero();

            sql = "UPDATE ad2223_mtirado.Pedido SET idFactura =" + idFactura + ", idProducto=" + idProducto + ", cantidad=" + cantidad + " WHERE idPedido =" + idPedido + ";";
            st.executeUpdate(sql);
            sql = "SELECT * FROM ad2223_mtirado.Pedido WHERE idPedido=" + idPedido;
            imprimirPedido(st, sql);

        } catch (SQLException e) {
            System.out.println("Error en el SQL");
        }

    }

    /***
     * Imprime 1 registro de Pedido de la consulta pasado por parámetro
     * @param st Statement
     * @param sql Sentencia sql de la que queremos imprimir el primer resultado
     */
    private static void imprimirPedido(Statement st, String sql) {
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("IdPedido:" + rs.getInt("idPedido") + ", idFactura:" + rs.getInt("idFactura") + ", idProducto:" + rs.getInt("idProducto") + ", cantidad:" + rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Procedimiento que permite modificar un registro de la tabla Productos, escogiéndolo por su id
     * @param st Statement
     */
    public static void modificarProducto(Statement st) {
        int idProducto;
        String denominacion;
        float precio;
        String sql;

        try {
            sql = "SELECT * FROM ad2223_mtirado.Productos ORDER BY idProducto ASC LIMIT 1";
            imprimirProducto(st, sql);

            System.out.println("...");

            sql = "SELECT * FROM ad2223_mtirado.Productos ORDER BY idProducto DESC LIMIT 1";
            imprimirProducto(st, sql);


            System.out.println();
            System.out.println("Seleccione el id del producto que desea modificar:");
            idProducto = Utilidades.validarEntero();

            sql = "SELECT * FROM ad2223_mtirado.Productos WHERE idProducto=" + idProducto;
            imprimirProducto(st, sql);

            System.out.println("Modificación:");
            System.out.print("denominación ");
            denominacion = new Scanner(System.in).nextLine();
            System.out.print("Precio ");
            precio = Utilidades.validarFloat();

            sql = "UPDATE ad2223_mtirado.Productos SET denominacion ='" + denominacion + "', precio=" + precio + " WHERE idProducto =" + idProducto + ";";
            st.executeUpdate(sql);
            sql = "SELECT * FROM ad2223_mtirado.Productos WHERE idProducto=" + idProducto;
            imprimirProducto(st, sql);

        } catch (SQLException e) {
            System.out.println("Error en el SQL");
        }

    }

    /***
     * Imprime 1 registro de Producto de la consulta pasado por parámetro
     * @param st Statement
     * @param sql Sentencia sql de la que queremos imprimir el primer resultado
     */
    private static void imprimirProducto(Statement st, String sql) {
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("IdProducto:" + rs.getInt("idProducto") + ", denominación:" + rs.getString("denominacion") + ", precio:" + rs.getFloat("precio"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
