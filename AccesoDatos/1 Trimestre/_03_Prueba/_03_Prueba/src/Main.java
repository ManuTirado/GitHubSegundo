import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Statement st = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String servidor = "jdbc:mysql://dns11036.phdns11.es";
            Connection conection = DriverManager.getConnection(servidor, "ad2223", "nervion");
            if (conection != null) {
                st = conection.createStatement();

                //seleccionarPersonaEnRangoEdad(st, 24, 32);
                //seleccionarPorIncialesYordenarMayorAmenor(st, "C", "A");
                //ordenarPorLetra(st, "J");
                //ejer10(st);
                //ejer9(st);
                //ejer8(st);
                //ejer7(st);
                //ejer6(st);
                //ejer5(st);
                //empiezePorJYOrdenadoPorApellido(st);
                //mostrarMayoresde30(st);
                //ordenarPorEdad(st);
                //hacerInserccion(st);
                crearTabla(st, "mtirado", new String[]{"Id int PRIMARY KEY AUTO_INCREMENT", "nombre varchar(255)", "apellidos varchar(255)", "edad int"});
                //System.out.println(st.toString());
                //borrarTabla(st,"mtirado");
                st.close();
            } else {
                System.out.println("Error ");
            }
            if (st != null) {
                conection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void seleccionarPersonaEnRangoEdad (Statement st, int min, int max) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado WHERE edad BETWEEN " + min + " AND " + max;
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    private static void seleccionarPorIncialesYordenarMayorAmenor(Statement st, String inicialNombre, String inicialApellido) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado WHERE nombre LIKE '" + inicialNombre + "%' AND apellidos LIKE '"+ inicialApellido + "%'ORDER BY edad desc";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    /***
     * Imprime por consola las personas cuyo nombre comienze por la letra pasada por parámetro y los ordena por apellido
     * @param st
     * @param letraNombre
     * @throws SQLException
     */
    private static void ordenarPorLetra(Statement st, String letraNombre) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado WHERE nombre LIKE '" + letraNombre + "%' ORDER BY apellidos";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    private static void ejer10(Statement st) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado where edad > 65";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    private static void ejer9(Statement st) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado where edad > 65";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    private static void ejer8(Statement st) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado where edad between 24 and 32";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    private static void ejer7(Statement st) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado where apellidos like '%oh%' or apellidos like '%ma%'";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    private static void ejer6(Statement st) throws SQLException {
        String sql = "SELECT AVG(edad) AS 'media edad' FROM ad2223.mtirado";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getInt("media edad"));
    }

    private static void ejer5(Statement st) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado where nombre like 'C%' and apellidos like 'A%' order by edad desc ";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    private static void empiezePorJYOrdenadoPorApellido(Statement st) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado where nombre like 'J%' order by apellidos";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    private static void mostrarMayoresde30(Statement st) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado where edad > 30";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    private static void ordenarPorEdad(Statement st) throws SQLException {
        String sql = "SELECT * FROM ad2223.mtirado order by edad";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " " + rs.getString("edad"));
    }

    public static void crearTabla(Statement st, String tabla, String[] campos) {
        String sql = "CREATE TABLE ad2223." + tabla + "(";
        sql += campos[0];
        for (int i = 1; i < campos.length; i++) {
            sql += "," + campos[i];
        }
        sql = sql + " )";
        System.out.println(sql);

    }

    public static void borrarTabla(Statement st, String tabla) {
        String sql = "DROP TABLE ad2223." + tabla + ";";
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void hacerInserccion(Statement st) throws SQLException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\datos.txt"));
            String cadena;
            cadena = br.readLine();
            while (cadena != null) {
                st.executeUpdate(cadena);
                cadena = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}