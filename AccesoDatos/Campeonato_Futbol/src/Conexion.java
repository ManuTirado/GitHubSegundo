import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String SERVIDOR = "jdbc:mysql://dns11036.phdns11.es";
    private static final String USER = "ad2223_mtirado";
    private static final String PASSWORD = "1234";

    /***
     * Función que crea y devuelve una connection al servidor,
     * los datos de acceso los obtiene de las propiedades finales de la clase
     * @return Connection
     */
    public static Connection conectar() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(SERVIDOR, USER, PASSWORD);
            System.out.println("Bienvenido " + USER + " 👋👋🔥");
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
