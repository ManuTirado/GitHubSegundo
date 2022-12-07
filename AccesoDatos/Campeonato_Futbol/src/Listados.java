import Entidades.clsEquipo;
import Entidades.clsPartido;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Listados {

    /**
     * Lee la tabla equipos de la BBDD y devuelve un arraylist con los registros de esta
     * @return arraylist de clsEquipo
     */
    public static ArrayList<clsEquipo> leerEquiposDeLaBBDD() {
        ArrayList<clsEquipo> listaEquipos = new ArrayList<>();
        String sql = "SELECT * FROM " + Main.BASE_DATOS + ".Equipos;";
        try {
            ResultSet rs = Main.st.executeQuery(sql);
            while (rs.next()) {
                clsEquipo equipo = new clsEquipo();
                equipo.setIdEquipo(rs.getString(1));
                equipo.setGanados(rs.getInt(2));
                equipo.setEmpatados(rs.getInt(3));
                equipo.setPerdidos(rs.getInt(4));
                equipo.setGolesMarcados(rs.getInt(5));
                equipo.setGolesRecibidos(rs.getInt(6));
                listaEquipos.add(equipo);
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
        return listaEquipos;
    }

    /**
     * Devuelve un arraylist con los equipos ganadores de una tabla (octavos, cuartos, etc)
     * @param tabla tabla con los partidos de los cuales queremos obtener los equipos ganadores.
     * @return aarraylist de clsEquipo con los ganadores
     */
    public static ArrayList<clsEquipo> obtenerGanadoresDeBBDD(String tabla) {
        ArrayList<clsEquipo> equiposGanadores = new ArrayList<>();

        ArrayList<clsPartido> partidos = obtenerResultados(tabla);
        for (clsPartido p :
                partidos) {
            if (p.getGolesA() > p.getGolesB()) {
                equiposGanadores.add(new clsEquipo(p.getEquipoA()));
            } else if (p.getGolesB() > p.getGolesA()) {
                equiposGanadores.add(new clsEquipo(p.getEquipoB()));
            }
        }
        return equiposGanadores;
    }

    /**
     * Obtiene un arraylist con los partidos de una tabla (octavos, cuartos, etc)
     * @param tabla tabla con los partidos
     * @return arraylost de clsPartido
     */
    public static ArrayList<clsPartido> obtenerResultados(String tabla) {
        ArrayList<clsPartido> partidos = new ArrayList<>();
        String sql = "SELECT * FROM " + Main.BASE_DATOS + "." + tabla + ";";
        try {
            ResultSet rs = Main.st.executeQuery(sql);
            while (rs.next()) {
                clsPartido partido = new clsPartido();
                partido.setEquipoA(rs.getString(1));
                partido.setEquipoB(rs.getString(2));
                partido.setGolesA(rs.getInt(3));
                partido.setGolesB(rs.getInt(4));
                partidos.add(partido);
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
        return partidos;
    }
}
