import java.sql.*;

public class ConexionBD {

    private static String servidor = "jdbc:mysql://dns11036.phdns11.es?";
    private static Connection connection_;
    private static Statement st_ = null;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection_ = DriverManager.getConnection(servidor, "ad2223","nervion");
            if (connection_ != null) {
                st_ = connection_.createStatement();
                System.out.println("Conexión a base de datos test correcta");
                System.out.println(st_.toString());
                // crearTabla(st_, "mtirado", new String[]{"id int NOT NULL", "apellidos varchar(255)", "edad int"});
            }
            else {
                System.out.println("Conexión fallida");
            }
            connection_.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static int crearTabla (Statement st_, String tabla, String[] campos) throws SQLException {
        String sql = "CREATE TABLE ad2223." + tabla + "(";
        for (int i = 0; i < campos.length; i++) {
            sql += campos[i];
            if (i < campos.length-1) {
               sql += ", ";
            }
        }
        sql += ");";

        return st_.executeUpdate(sql);
    }
}
