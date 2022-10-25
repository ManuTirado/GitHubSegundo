import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static final String USER = "mtirado";
    private static final String PASSWORD = "Remolacha";
    private static final String BASE_DATOS = "mtirado";

    public static void main(String[] args) {
        Statement st = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String servidor = "jdbc:mysql://dns11036.phdns11.es";
            Connection conection = DriverManager.getConnection(servidor, USER, PASSWORD);

            if (conection != null) {
                st = conection.createStatement();

                cambiarContrasena(st, "Remolacha");

                st.close();
            } else {
                System.out.println("Error");
            }
            if (st != null) {
                conection.close();
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void cambiarContrasena(Statement st, String nuevaContraseña) {
        String sql = "SET PASSWORD FOR '" + BASE_DATOS + "' = password('" + nuevaContraseña + "')";
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}