import EntidadesHibernate.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

    private static DAL_Hibernate dal;

    public static void main(String[] args) {
        dal = new DAL_Hibernate();
        int opc_seleccionada;
        do {
            Utilidades.mostrarMenu();
            opc_seleccionada = Utilidades.validarEntero(0, 7);

            switch (opc_seleccionada) {
                case 1 -> NuevoIngreso();
                case 2 -> NuevaPareja();
                case 3 -> NuevaActividad();
                case 4 -> NuevoEntrenamiento();
                case 5 -> ActualizarMejor();
                case 6 -> mostrarExperienciaAcumulada();
                case 7 -> mostrarRanking();
                case 0 -> System.out.println("Adios! 👋");
            }
        } while (opc_seleccionada != 0);
    }

    //region Métodos menú
    private static void NuevoIngreso() {
        System.out.println("Ingrese un Nombre");
        String nombre = Utilidades.leerString();
        System.out.println("Ingrese los Apellidos");
        String apellidos = Utilidades.leerString();
        IE_Estudiantes estudiante = new IE_Estudiantes(nombre, apellidos, 2, 0);
        dal.abrir();
        dal.insertar(estudiante);
        dal.cerrar();
    }

    private static void NuevaPareja() {
        IE_Estudiantes estudiante1;
        do {
            System.out.println("Ingrese el ID del Estudiante 1");
            int idEst1 = Utilidades.validarEntero();
            dal.abrir();
            estudiante1 = dal.leer(idEst1, IE_Estudiantes.class);
            dal.cerrar();
            if (estudiante1 == null) {
                System.out.println("No existe el estudiante con el id '" + idEst1 + "'");
            }
        } while (estudiante1 == null);

        IE_Estudiantes estudiante2;
        do {
            System.out.println("Ingrese el ID del Estudiante 2");
            int idEst2 = Utilidades.validarEntero();
            dal.abrir();
            estudiante2 = dal.leer(idEst2, IE_Estudiantes.class);
            dal.cerrar();
            if (estudiante2 == null) {
                System.out.println("No existe el estudiante con el id '" + idEst2 + "'");
            }
            if (estudiante2.equals(estudiante1)) {
                estudiante2 = null;
                System.out.println("No puede ingresar el mismo estudiante");
            }
        } while (estudiante2 == null);

        LocalDate fechaInicio, fechaFin;
        do {
            System.out.println("Ingrese la Fecha de Inicio del grupo");
            fechaInicio = Utilidades.leerFecha();
            System.out.println("Ingrese la Fecha de Fin del grupo");
            fechaFin = Utilidades.leerFecha();
            if (fechaInicio.isAfter(fechaFin)) {
                System.out.println("La fecha de Fin no puede ser anterior a la de Inicio");
            }
        } while (fechaInicio.isAfter(fechaFin));

        IE_Parejas pareja = new IE_Parejas(fechaInicio, fechaFin, estudiante1, estudiante2);
        dal.abrir();
        dal.insertar(pareja);
        dal.cerrar();
    }

    private static void NuevaActividad() {
        System.out.println("Ingrese la Descripción de la Actividad");
        String descripcion = Utilidades.leerString();
        LocalDate fechaInicio, fechaFin;
        do {
            System.out.println("Ingrese la Fecha de Inicio del grupo");
            fechaInicio = Utilidades.leerFecha();
            System.out.println("Ingrese la Fecha de Fin del grupo");
            fechaFin = Utilidades.leerFecha();
            if (fechaInicio.isAfter(fechaFin)) {
                System.out.println("La fecha de Fin no puede ser anterior a la de Inicio");
            }
        } while (fechaInicio.isAfter(fechaFin));
        System.out.println("Ingrese la Experiencia");
        int experiencia = Utilidades.validarEntero();

        IE_Actividades actividad = new IE_Actividades(descripcion, fechaInicio, fechaFin, experiencia);
        dal.abrir();
        dal.insertar(actividad);
        dal.cerrar();
    }

    private static void NuevoEntrenamiento() {
        IE_Parejas pareja;
        do {
            System.out.println("Ingrese el ID de la Pareja");
            int idPar = Utilidades.validarEntero();
            dal.abrir();
            pareja = dal.leer(idPar, IE_Parejas.class);
            dal.cerrar();
            if (pareja == null) {
                System.out.println("No existe la pareja con el id '" + idPar + "'");
            }
        } while (pareja == null);
        IE_Actividades actividad;
        do {
            System.out.println("Ingrese el ID de la Actividad");
            int idAct = Utilidades.validarEntero();
            dal.abrir();
            actividad = dal.leer(idAct, IE_Actividades.class);
            dal.cerrar();
            if (actividad == null) {
                System.out.println("No existe la actividad con el id '" + idAct + "'");
            }
        } while (actividad == null);
        LocalDate fechaEntrenamiento;
        do {
            System.out.println("Ingrese la Fecha de realización del Entrenamiento");
            fechaEntrenamiento = Utilidades.leerFecha();
            if (fechaEntrenamiento.isBefore(actividad.getFechaInicio()) || fechaEntrenamiento.isAfter(actividad.getFechaFin())) {
                System.out.println("La fehca de entrenamiento debe estar comprendida entre las fechas '" + actividad.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "' y '" + actividad.getFechaFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "'");
            }
        } while (fechaEntrenamiento.isBefore(actividad.getFechaInicio()) || fechaEntrenamiento.isAfter(actividad.getFechaFin()));
        IE_entrenamientos entrenamiento = new IE_entrenamientos(fechaEntrenamiento, actividad, null, pareja);
        dal.abrir();
        dal.insertar(entrenamiento);
        dal.cerrar();
    }

    private static void ActualizarMejor() {
        IE_entrenamientos entrenamiento;
        do {
            System.out.println("Ingrese el ID del Entrenamiento");
            int idEnt = Utilidades.validarEntero();
            dal.abrir();
            entrenamiento = dal.leer(idEnt, IE_entrenamientos.class);
            dal.cerrar();
            if (entrenamiento == null) {
                System.out.println("No existe el entrenamiento con el id '" + idEnt + "'");
            }
        } while (entrenamiento == null);
        IE_Estudiantes estudiante;
        do {
            System.out.println("Ingrese el ID del Estudiante 2");
            int idEst = Utilidades.validarEntero();
            dal.abrir();
            estudiante = dal.leer(idEst, IE_Estudiantes.class);
            dal.cerrar();
            if (estudiante == null) {
                System.out.println("No existe el estudiante con el id '" + idEst + "'");
            }
        } while (estudiante == null);
        if (entrenamiento.getBestEst() != null) {
            dal.abrir();
            entrenamiento.setBestEst(estudiante);
            dal.actualziar(entrenamiento);
            estudiante.setExpAcumulada(estudiante.getExpAcumulada() + entrenamiento.getActividad().getExperiencia());
            dal.cerrar();
        } else {
            System.out.println("Ya hay un mejor estudiante");
        }
    }

    private static void mostrarExperienciaAcumulada() {
        dal.abrir();
        List<IE_Estudiantes> estudiantes = dal.leerTodosRegistros(IE_Estudiantes.class);
        dal.cerrar();
        for (IE_Estudiantes e :
                estudiantes) {
            System.out.println(e.getID() + " => " + e.getNombre() + " " + e.getApellidos() + ": " + e.getExpAcumulada() + " puntos");
        }

    }

    private static void mostrarRanking() {
        dal.abrir();
        List<IE_Estudiantes> estudiantes = dal.leerNamedQuery("mejoresEstudiantes");
        dal.cerrar();
        for (IE_Estudiantes e :
                estudiantes) {
            System.out.println(e.getID() + " => " + e.getNombre() + " " + e.getApellidos() + ": ");
        }
    }
    //endregion


}
