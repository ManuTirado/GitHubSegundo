import Entidades.clsEquipo;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    // region BBDD
    private static final String SERVIDOR = "jdbc:mysql://dns11036.phdns11.es";
    private static final String USER = "ad2223_mtirado";
    private static final String PASSWORD = "1234";
    private static final String BASE_DATOS = "ad2223_mtirado";

    private static Connection connection;
    private static Statement st;
    //endregion
    private static final String RUTA_INPUT_EQUIPOS = "src\\equipos.txt";
    private static ArrayList<clsEquipo> equipos = new ArrayList<>() {
    };

    public static void main(String[] args) {
        try {
            connection = conectar();
            if (connection != null) {
                st = connection.createStatement();
                crearTablas();
                obtenerEquiposDelTxt();
                vaciarTablaEquipo();
                insertarEquiposEnBBDD();
                System.out.println(leerEquipoDeLaBBDD("España").toString());

                st.close();
                if (st != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //region Métodos
    private static clsEquipo leerEquipoDeLaBBDD(String nombreEquipo) {
        String sql = "SELECT * FROM " + BASE_DATOS + ".Equipos WHERE idEquipo = '" + nombreEquipo + "';";
        try {
            ResultSet rs = st.executeQuery(sql);
            clsEquipo equipo = new clsEquipo();
            if (rs.next()) {
                equipo.setIdEquipo(rs.getString(1));
                equipo.setGanados(rs.getInt(2));
                equipo.setEmpatados(rs.getInt(3));
                equipo.setPerdidos(rs.getInt(4));
                equipo.setGolesMarcados(rs.getInt(5));
                equipo.setGolesRecibidos(rs.getInt(6));
            }
            return equipo;
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            return null;
        }
    }

    private static void insertarEquiposEnBBDD() {
        for (clsEquipo e :
                equipos) {
            insertarEquipo(e.getIdEquipo());
        }
    }

    private static void insertarEquipo(String idEquipo) {
        String sql = "INSERT INTO " + BASE_DATOS + ".Equipos (idEquipo, ganados, empatados, perdidos, golesMarcados, golesRecibidos)" +
                " VALUES ('" + idEquipo + "', 0, 0, 0, 0, 0)";
        try {
            st.executeUpdate(sql);
        } catch (SQLException sqlException) {
            System.err.println("Error al insertar el equipo " + idEquipo + " en la BBDD");
            throw new RuntimeException(sqlException);
        }
    }

    private static void obtenerEquiposDelTxt() {
        File input = new File(RUTA_INPUT_EQUIPOS);
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line = br.readLine();
            while (line != null) {
                equipos.add(new clsEquipo(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error al intentar leer los equipos");
            throw new RuntimeException(e);
        }
    }

    public static void vaciarTablaEquipo() {
        String sql = "TRUNCATE TABLE " + BASE_DATOS + ".Equipos";
        try {
            st.executeUpdate(sql);
        } catch (SQLException sqlException) {
            System.err.println("Error al insertar el equipo en la BBDD");
            throw new RuntimeException(sqlException);
        }
    }

    private static void crearTablas() {
        crearTabla("Equipos", "idEquipo VARCHAR(25) PRIMARY KEY, ganados INT, empatados INT, perdidos INT, golesMarcados INT, golesRecibidos INT".split(","));
        crearTabla("Octavos", new String[]{"equipoA VARCHAR(25)", "equipoB VARCHAR(25)", "golesA INT", "golesB INT"});
        crearTabla("Cuartos", new String[]{"equipoA VARCHAR(25)", "equipoB VARCHAR(25)", "golesA INT", "golesB INT"});
        crearTabla("Semifinales", new String[]{"equipoA VARCHAR(25)", "equipoB VARCHAR(25)", "golesA INT", "golesB INT"});
        crearTabla("Final", new String[]{"equipoA VARCHAR(25)", "equipoB VARCHAR(25)", "golesA INT", "golesB INT"});
    }
    //endregion

    //region Utilidades

    /***
     * Procedimiento que crea una tabla en la base de datos definida en las propiedades finales de la clase
     * @param tabla Nombre tabla
     * @param campos Nombre campo + tipo de dato + (si procede) Extras como AUTO_INCREMENT...
     */
    private static void crearTabla(String tabla, String[] campos) {
        String sql = "CREATE TABLE IF NOT EXISTS " + BASE_DATOS + "." + tabla + "(";
        sql += campos[0];
        for (int i = 1; i < campos.length; i++) {
            sql += "," + campos[i];
        }
        sql += " )";
        System.out.println(sql);
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void mostrarMenu() {
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /  /  /  /");
        System.out.println("1. Iniciar Octavos");
        System.out.println("2. Iniciar Cuartos");
        System.out.println("3. Iniciar Semifinales");
        System.out.println("4. Iniciar la Final");
        System.out.println("5. Iniciar la Final");
        System.out.println("6. Salir");
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /  /  /  /");
    }
    //endregion
}