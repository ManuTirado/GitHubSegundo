import java.sql.*;

public class Main {

    public static Connection conexion;

    public static void main(String[] args) {
        Statement st = null;
        conectar();
        if (conexion != null) {
            try {
                PreparedStatement ps = conexion.prepareStatement("SHOW TABLES");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
                //consulta1();
                //consulta2();
                //consulta3();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String servidor = "jdbc:mysql://dns11036.phdns11.es/ad2223_mtirado";
            conexion = DriverManager.getConnection(servidor, "ad2223_mtirado", "1234");
            System.out.println("Se ha conectado con exito :D");
        } catch (SQLException | ClassNotFoundException exception) {
            System.err.println(exception.getMessage());
        }


    }

    private static void consulta1() throws SQLException {
        int contador = 0;
        PreparedStatement ps = conexion.prepareStatement("SELECT NombreCompañia, NombreContacto, Ciudad FROM Clientes");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contador++;
            System.out.println(contador + " Nombre Compañia: " + rs.getString("NombreCompañia") + " Nombre Contacto: " + rs.getString("NombreContacto") + " Ciudad: " + rs.getString("Ciudad"));
        }
    }

    private static void consulta2() throws SQLException {
        int contador = 0;
        PreparedStatement ps = conexion.prepareStatement("SELECT NombreContacto, Direccion, CodPostal FROM Clientes order by CodPostal DESC, Direccion DESC ");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contador++;
            System.out.println(contador + " Nombre Contacto: " + rs.getString("NombreContacto") + "  Direccion: " + rs.getString("Direccion") + " CodPostal: " + rs.getString("CodPostal"));
        }
    }

    private static void consulta3() throws SQLException {
        int contador = 0;
        PreparedStatement ps = conexion.prepareStatement("SELECT Productos.NombreProducto, Productos.IdCategoria, Productos.PrecioUnidad FROM Productos INNER JOIN Categorias ON Productos.IdCatgoria = Categorias.IdCategoria order by Categorias.NombreCategoria, Productos.NombreProducto ");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contador++;
            System.out.println(contador + " NombreProducto: " + rs.getString(1) + "  IdCategoria: " + rs.getString(2) + " Precio Unidad: " + rs.getString(3));
        }
    }


}
