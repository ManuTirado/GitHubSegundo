import java.sql.*;

public class Listados {
    /***
     * Procedimiento que muestra por consola todos los registros de la tabla Mesa
     * @param st Statement
     */
    public static void listarMesa(Statement st) {
        System.out.println("Registros de Mesa:");
        String sql = "SELECT * FROM ad2223_mtirado.Mesa";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println("IdMesa:" + rs.getInt("idMesa") + ", numComensales:" + rs.getInt("numComensales") + ", reserva:" + rs.getInt("reserva"));
        } catch (SQLException e) {
            System.out.println("Error en el Select de Mesa");
        }
    }

    /***
     * Procedimiento que muestra por consola todos los registros de la tabla Facturas
     * @param st Statement
     */
    public static void listarFactura(Statement st) {
        System.out.println("Registros de Factura:");
        String sql = "SELECT * FROM ad2223_mtirado.Factura";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println("idFactura:" + rs.getInt("idFactura") + ", idMesa:" + rs.getInt("idMesa") + ", tipoPago:" + rs.getString("tipoPago") + ", importe:" + rs.getFloat("importe"));
        } catch (SQLException e) {
            System.out.println("Error en el Select de Factura");
        }
    }

    /***
     * Procedimiento que muestra por consola todos los registros de la tabla Pedido
     * @param st Statement
     */
    public static void listarPedido(Statement st) {
        System.out.println("Registros de Pedido:");
        String sql = "SELECT * FROM ad2223_mtirado.Pedido";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println("idPedido:" + rs.getInt("idPedido") + ", idFactura:" + rs.getInt("idFactura") + ", idProducto:" + rs.getInt("idProducto") + ", cantidad:" + rs.getInt("cantidad"));
        } catch (SQLException e) {
            System.out.println("Error en el Select de Factura");
        }
    }

    /***
     * Procedimiento que muestra por consola todos los registros de la tabla Productos
     * @param st Statement
     */
    public static void listarProductos(Statement st) {
        System.out.println("Registros de Productos:");
        String sql = "SELECT * FROM ad2223_mtirado.Productos";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println(rs.getInt("idProducto") + " " + rs.getString("denominacion") + " " + rs.getFloat("precio"));
        } catch (SQLException e) {
            System.out.println("Error en el Select de Factura");
        }
    }

    /***
     * Procedimiento que lista todos los registros de todas las tablas
     * @param st Statement
     */
    public static void listarTodo(Statement st) {
        listarMesa(st);
        System.out.println();
        listarFactura(st);
        System.out.println();
        listarPedido(st);
        System.out.println();
        listarProductos(st);
        System.out.println();
    }
}
