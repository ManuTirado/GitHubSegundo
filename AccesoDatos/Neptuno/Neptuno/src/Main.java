import java.sql.*;

public class Main {

    private static final String SERVIDOR = "jdbc:mysql://dns11036.phdns11.es/ad2223_neptuno";
    private static final String USER = "dberal";
    private static final String PASSWORD = "nervion";

    public static void main(String[] args) {

        Connection connection = conectar();
        if (connection != null) {
            //consulta1(connection);
            //consulta2(connection);
            consulta5(connection);
        }
    }

    private static void consulta1(Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT NombreCompañia, NombreContacto, Ciudad FROM Clientes");
            ResultSet rs = ps.executeQuery();
            int cont = 0;
            while (rs.next()) {
                cont++;
                System.out.println(cont+" => Nombre Compañía:" + rs.getString(1) + ", Nombre Contacto: " + rs.getString(2) + ", Ciudad: " + rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void consulta2(Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT NombreContacto, Direccion, CodPostal FROM Clientes ORDER BY CodPostal DESC,Direccion DESC");
            ResultSet rs = ps.executeQuery();
            int cont = 0;
            while (rs.next()) {
                cont++;
                System.out.println(cont+" => Nombre Contacto:" + rs.getString(1) + ", Dirección: " + rs.getString(2) + ", CP: " + rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void consulta5(Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Productos.NombreProducto, Categorias.NombreCategoria, Productos.PrecioUnidad FROM Productos LEFT JOIN Categorias ON Productos.IdCategoria=Categorias.IdCategoria ORDER BY Categorias.NombreCategoria,Productos.NombreProducto");
            ResultSet rs = ps.executeQuery();
            int cont = 0;
            ResultSetMetaData rsm = rs.getMetaData();
            while (rs.next()) {
                cont++;
                System.out.println(cont+" => "+ rsm.getColumnName(1) + ": " + rs.getString(1) + ", Nombre Categoría: " + rs.getString(2) + ", Precio/u: " + rs.getString(3) + "€");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection conectar() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(SERVIDOR, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("No se ha podido conectar a la base de datos");
            connection = null;
        } catch (ClassNotFoundException e) {
            System.out.println("Error con el jdbc driver");
            connection = null;
        }
        return connection;
    }
}