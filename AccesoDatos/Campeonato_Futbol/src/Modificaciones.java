import Entidades.clsEquipo;
import Entidades.clsPartido;

import java.sql.SQLException;

public class Modificaciones {
    /***
     * Procedimiento que crea una tabla en la base de datos definida en las propiedades finales de la clase
     * @param tabla Nombre tabla
     * @param campos Nombre campo + tipo de dato + (si procede) Extras como AUTO_INCREMENT...
     */
    public static void crearTabla(String tabla, String[] campos) {
        String sql = "CREATE TABLE IF NOT EXISTS " + Main.BASE_DATOS + "." + tabla + "(";
        sql += campos[0];
        for (int i = 1; i < campos.length; i++) {
            sql += "," + campos[i];
        }
        sql += " )";
        try {
            Main.st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Vacua una tabla de la base de datos
     * @param tabla
     */
    public static void vaciarTabla(String tabla) {
        String sql = "TRUNCATE TABLE " + Main.BASE_DATOS + "." + tabla;
        try {
            Main.st.executeUpdate(sql);
        } catch (SQLException sqlException) {
            System.err.println("Error al vaciar la tabla " + tabla);
            throw new RuntimeException(sqlException);
        }
    }

    /**
     * Actualiza los equipos del partido pasado por parámetro en la tabla equipos.
     * Según el resultado del partido añade una victorio, derrota o empate a cada equipo del partido.
     * Además de sumarle los goles marcados y recibidos.
     * @param partido
     */
    public static void actualizarEquipos(clsPartido partido) {
        String sql = "";
        clsEquipo equipoDesActualizadoA;
        clsEquipo equipoDesActualizadoB;
        try {
            equipoDesActualizadoA = Manejadora.leerEquipoDeLaBBDD(partido.getEquipoA());
            equipoDesActualizadoB = Manejadora.leerEquipoDeLaBBDD(partido.getEquipoB());
            if (partido.getGolesA() > partido.getGolesB()) {
                sql = "UPDATE " + Main.BASE_DATOS
                        + ".Equipos SET ganados = " + (equipoDesActualizadoA.getGanados() + 1) +
                        ", golesMarcados = " + (equipoDesActualizadoA.getGolesMarcados() + partido.getGolesA()) +
                        ", golesRecibidos = " + (equipoDesActualizadoA.getGolesRecibidos() + partido.getGolesB()) +
                        " WHERE idEquipo = '" + partido.getEquipoA() + "';";
                Main.st.executeUpdate(sql);
                sql = "UPDATE " + Main.BASE_DATOS
                        + ".Equipos SET perdidos = " + (equipoDesActualizadoB.getPerdidos() + 1) +
                        ", golesMarcados = " + (equipoDesActualizadoB.getGolesMarcados() + partido.getGolesB()) +
                        ", golesRecibidos = " + (equipoDesActualizadoB.getGolesRecibidos() + partido.getGolesA()) +
                        " WHERE idEquipo = '" + partido.getEquipoB() + "';";
                Main.st.executeUpdate(sql);
            } else if (partido.getGolesB() > partido.getGolesA()) {
                sql = "UPDATE " + Main.BASE_DATOS
                        + ".Equipos SET ganados = " + (equipoDesActualizadoB.getGanados() + 1) +
                        ", golesMarcados = " + (equipoDesActualizadoB.getGolesMarcados() + partido.getGolesB()) +
                        ", golesRecibidos = " + (equipoDesActualizadoB.getGolesRecibidos() + partido.getGolesA()) +
                        " WHERE idEquipo = '" + partido.getEquipoB() + "';";
                Main.st.executeUpdate(sql);
                sql = "UPDATE " + Main.BASE_DATOS
                        + ".Equipos SET perdidos = " + (equipoDesActualizadoA.getPerdidos() + 1) +
                        ", golesMarcados = " + (equipoDesActualizadoA.getGolesMarcados() + partido.getGolesA()) +
                        ", golesRecibidos = " + (equipoDesActualizadoA.getGolesRecibidos() + partido.getGolesB()) +
                        " WHERE idEquipo = '" + partido.getEquipoA() + "';";
                Main.st.executeUpdate(sql);
            } else {
                sql = "UPDATE " + Main.BASE_DATOS
                        + ".Equipos SET empatados = " + (equipoDesActualizadoA.getEmpatados() + 1) +
                        ", golesMarcados = " + (equipoDesActualizadoA.getGolesMarcados() + partido.getGolesA()) +
                        ", golesRecibidos = " + (equipoDesActualizadoA.getGolesRecibidos() + partido.getGolesB()) +
                        " WHERE idEquipo = '" + partido.getEquipoA() + "';";
                Main.st.executeUpdate(sql);
                sql = "UPDATE " + Main.BASE_DATOS
                        + ".Equipos SET empatados = " + (equipoDesActualizadoB.getEmpatados() + 1) +
                        ", golesMarcados = " + (equipoDesActualizadoB.getGolesMarcados() + partido.getGolesB()) +
                        ", golesRecibidos = " + (equipoDesActualizadoB.getGolesRecibidos() + partido.getGolesA()) +
                        " WHERE idEquipo = '" + partido.getEquipoB() + "';";
                Main.st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
