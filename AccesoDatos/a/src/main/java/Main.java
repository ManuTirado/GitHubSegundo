import EntidadesHibernate.Alumnado;
import EntidadesHibernate.Direccion;

public class Main {

    private static  AccessBBDD instancia;

    public static void main(String[] args) throws Exception {
        instancia = new AccessBBDD();
        // guardar()
        insertarAlumno();
    }

    private static void insertarAlumno() throws Exception {
        Direccion direccion = new Direccion(1, "Plaza del ayuntamiento", 8, "Xativa", "Sevilla");
        Alumnado alumno = new Alumnado("Manuel", "Tirado", "García");
        alumno.setDireccion(direccion);
        guardar(alumno);
    }

    private static void guardar(Object cosa) {
        instancia.abrir();
        int id = (int) instancia.guardar(cosa);
        System.out.println(id);
        instancia.cerrar();
    }
}
