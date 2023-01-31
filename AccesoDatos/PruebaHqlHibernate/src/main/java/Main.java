import EntidadesHibernate.tAlumnado;
import EntidadesHibernate.tAsignaturas;
import EntidadesHibernate.tDepartamento;
import EntidadesHibernate.tProfesores;

import javax.management.Query;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final DAL_Hibernate dal = new DAL_Hibernate();

    public static void main(String[] args) {
        // Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        int opc_menu_principal;
        do {
            Utilidades.imprimirMenuPrincipal();
            opc_menu_principal = Utilidades.validarEntero(0, 11);
            switch (opc_menu_principal) {
                case 0 -> System.out.println("Adios! 👋");
                case 1 -> insertarProfesoradoYasignaturas();
                case 2 -> matricularAlumnado();
                case 3 -> listarAsignaturas();
                case 4 -> listarProfesorado();
                case 5 -> listarAlumnado();
                case 6 -> listarAlumnadoMatriculado();
                case 7 -> buscarPorNombre();
                case 8 -> mostrarTotal();
                case 9 -> insertarDepartamento();
                case 10 -> asociarProfAdept();
                case 11 -> crearProfYDep();
            }
        } while (opc_menu_principal != 0);
    }

    private static void matricularAlumnado() {
        String nombre, ape1, ape2;
        System.out.println("Introduzca el nombre del alumnado");
        nombre = Utilidades.leerString();
        System.out.println("Introduzca el primer apellido del alumnado");
        ape1 = Utilidades.leerString();
        System.out.println("Introduzca el segundo apellido del alumnado");
        ape2 = Utilidades.leerString();
        dal.insertar(new tAlumnado(ape1, ape2, nombre));
    }

    private static void insertarProfesoradoYasignaturas() {
        String nombre, ape1, ape2;
        System.out.println("Introduzca el nombre del profesor");
        nombre = Utilidades.leerString();
        System.out.println("Introduzca el primer apellido del profesor");
        ape1 = Utilidades.leerString();
        System.out.println("Introduzca el segundo apellido del profesor");
        ape2 = Utilidades.leerString();
        dal.insertar(new tProfesores(ape1, ape2, nombre));
    }

    private static void listarAsignaturas() {
        ArrayList<tAsignaturas> asignaturas = dal.leerTodosRegistros(tAsignaturas.class);
        System.out.println("/  /  /  /  / ASIGNATURAS /  /  /  /  /");
        for (tAsignaturas a :
                asignaturas) {
            System.out.println(a.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /");
    }

    private static void listarProfesorado() {
        ArrayList<tProfesores> profesores = dal.leerTodosRegistros(tProfesores.class);
        //ArrayList<tProfesores> profesores = (ArrayList<tProfesores>) dal.leerNamedQuery("listaPorNombre","nombre","David");
        System.out.println("/  /  /  /  / PROFESORES /  /  /  /  /");
        for (tProfesores p :
                profesores) {
            System.out.println(p.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /");
    }

    private static void listarAlumnado() {
        ArrayList<tAlumnado> alumnados = dal.leerTodosRegistros(tAlumnado.class);
        System.out.println("/  /  /  /  / ALUMNADO /  /  /  /  /");
        for (tAlumnado a :
                alumnados) {
            System.out.println(a.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /");
    }

    private static void listarAlumnadoMatriculado() {
        //ArrayList<tAlumnado> alumnados = dal.leerTodosRegistros(tAlumnado.class);
        ArrayList<tAlumnado> alumnados = dal.leerNamedQuery("listaAlumnosMatriculados");
        System.out.println("/  /  /  / ALUMNADO MATRICULADO /  /  /  /");
        for (tAlumnado a :
                alumnados) {
            System.out.println(a.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /  /  /");
    }

    private static void buscarPorNombre() {

    }

    private static void mostrarTotal() {

    }

    private static void insertarDepartamento() {
        String nombre;
        System.out.println("Introduzca el nombre del departamento");
        nombre = Utilidades.leerString();
        dal.insertar(new tDepartamento(nombre));
    }

    private static void asociarProfAdept() {
        int id;
        System.out.println("Introduzca el id de un profesor");
        id = Utilidades.validarEntero();
        tProfesores profesor = dal.leer(id, tProfesores.class);
        System.out.println("Introduzca el id de un departamento");
        id = Utilidades.validarEntero();
        tDepartamento departamento = dal.leer(id, tDepartamento.class);

        profesor.setDepartamento(departamento);
        dal.insertar(profesor);
    }

    private static void crearProfYDep() {
        tDepartamento departamento = new tDepartamento("Lengua");
        tProfesores profesor = new tProfesores("Pocholo", "Martinez", "Bordiu");
        profesor.setDepartamento(departamento);
        dal.insertar(profesor);
    }
}
