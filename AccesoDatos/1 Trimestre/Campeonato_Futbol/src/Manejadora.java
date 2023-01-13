import Entidades.clsEquipo;
import Entidades.clsPartido;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Manejadora {

    /**
     * Inserta un equipo en la tabla equipos, estableciéndose solo su nombre.
     * @param idEquipo Nombre del equipo
     */
    public static void insertarEquipo(String idEquipo) {
        String sql = "INSERT INTO " + Main.BASE_DATOS + ".Equipos (idEquipo, ganados, empatados, perdidos, golesMarcados, golesRecibidos)" +
                " VALUES ('" + idEquipo + "', 0, 0, 0, 0, 0)";
        try {
            Main.st.executeUpdate(sql);
        } catch (SQLException sqlException) {
            System.err.println("Error al insertar el equipo " + idEquipo + " en la BBDD");
            throw new RuntimeException(sqlException);
        }
    }

    /**
     * Lee un equipo de la BBDD
     * @param nombreEquipo nombre del equipo buscado
     * @return equipo encontrado o null si no se ha encontrado
     */
    public static clsEquipo leerEquipoDeLaBBDD(String nombreEquipo) {
        String sql = "SELECT * FROM " + Main.BASE_DATOS + ".Equipos WHERE idEquipo = '" + nombreEquipo + "';";
        try {
            ResultSet rs = Main.st.executeQuery(sql);
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

    /**
     * Inserta un partido en la base de datos
     * @param tabla nombre de la tabla
     * @param partido partido a insertars
     */
    public static void insetarPartidoEnBBDD(String tabla, clsPartido partido) {
        String sql = "INSERT INTO " + Main.BASE_DATOS + "." + tabla + " (equipoA, equipoB, golesA, golesB)" +
                " VALUES ('" + partido.getEquipoA() + "', '" + partido.getEquipoB() + "', " + partido.getGolesA() + ", " + partido.getGolesB() + ")";
        try {
            Main.st.executeUpdate(sql);
        } catch (SQLException sqlException) {
            System.err.println("Error al insertar el partido entre " + partido.getEquipoA() + " y " + partido.getEquipoB() + " en la tabla " + tabla);
            throw new RuntimeException(sqlException);
        }
    }
}
