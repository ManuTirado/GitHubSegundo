import EntidadesHibernate.tAlumnado;
import EntidadesHibernate.tProfesores;

import javax.management.Query;
import java.util.ArrayList;

public class Main {

    private static final DAL_Hibernate dal = new DAL_Hibernate();

    public static void main(String[] args) {
        int opc_menu_principal;
        String tablaSeleccionada = "";
        do {
            Utilidades.imprimirMenuPrincipal();
            opc_menu_principal = Utilidades.validarEntero(0, 4);
            switch (opc_menu_principal) {
                case 0 -> System.out.println("Adios! 👋");
                case 1 -> tablaSeleccionada = "tAlumnado";
                case 2 -> tablaSeleccionada = "tAsignaturas";
                case 3 -> tablaSeleccionada = "tMatricula";
                case 4 -> tablaSeleccionada = "tProfesores";
            }
            int opc_menu_secundario = 1;
            while (opc_menu_secundario != 0 && opc_menu_principal != 0) {
                System.out.println("Tabla seleccionada => '" + tablaSeleccionada + "'");
                Utilidades.imprimirMenuSecundario();
                opc_menu_secundario = Utilidades.validarEntero(0, 4);
                switch (opc_menu_secundario) {
                    case 1 -> insertar(opc_menu_principal);
                    case 2 -> listar(opc_menu_principal);
                    case 3 -> modificar(opc_menu_principal);
                    case 4 -> borrar(opc_menu_principal);
                }
            }
        } while (opc_menu_principal != 0);
    }

    //region Métodos menú
    /**
     * Ejecuta el método de inserción adecuado según la tabla seleccionada
     * @param tablaSeleccionada, un entero que hace referencia a la tabla con el mismo número en el menú
     */
    private static void insertar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> insertarAlumnado();
            case 2 -> insertarAsignatura();
            case 3 -> insertarMatricula();
            case 4 -> insertarProfesor();
        }
    }

    /**
     * Ejecuta el método de listar adecuado según la tabla seleccionada
     * @param tablaSeleccionada, un entero que hace referencia a la tabla con el mismo número en el menú
     */
    private static void listar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> listarAlumnados();
            case 2 -> listarAsignaturas();
            case 3 -> listarMatriculas();
            case 4 -> listarProfesores();
        }
    }

    /**
     * Ejecuta el método de modificación adecuado según la tabla seleccionada
     * @param tablaSeleccionada, un entero que hace referencia a la tabla con el mismo número en el menú
     */
    private static void modificar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> modificarAlumnado();
            case 2 -> modificarAsignatura();
            case 3 -> modificarMatricula();
            case 4 -> modificarProfesor();
        }
    }

    /**
     * Ejecuta el método de borrado adecuado según la tabla seleccionada
     * @param tablaSeleccionada, un entero que hace referencia a la tabla con el mismo número en el menú
     */
    private static void borrar(int tablaSeleccionada) {
        switch (tablaSeleccionada) {
            case 1 -> borrarAlumnado();
            case 2 -> borrarAsignatura();
            case 3 -> borrarMatricula();
            case 4 -> borrarProfesor();
        }
    }
    //endregion

    //region Métodos insertar
    private static void insertarAlumnado() {
        String nombre, ape1, ape2;
        System.out.println("Introduzca el nombre del alumnado");
        nombre = Utilidades.leerString();
        System.out.println("Introduzca el primer apellido del alumnado");
        ape1 = Utilidades.leerString();
        System.out.println("Introduzca el segundo apellido del alumnado");
        ape2 = Utilidades.leerString();
        dal.insertar(new tAlumnado(ape1,ape2,nombre));
    }
    private static void insertarMatricula() {
    }

    private static void insertarAsignatura() {

    }

    private static void insertarProfesor() {
        String nombre, ape1, ape2;
        System.out.println("Introduzca el nombre del profesor");
        nombre = Utilidades.leerString();
        System.out.println("Introduzca el primer apellido del profesor");
        ape1 = Utilidades.leerString();
        System.out.println("Introduzca el segundo apellido del profesor");
        ape2 = Utilidades.leerString();
        dal.insertar(new tProfesores(ape1,ape2,nombre));
    }
    //endregion


    //region Métodos listar
    private static void listarAlumnados() {

    }
    private static void listarMatriculas() {
    }

    private static void listarAsignaturas() {

    }

    private static void listarProfesores() {
        //ArrayList<tProfesores> profesores = dal.leerTodosRegistros(tProfesores.class);
        ArrayList<tProfesores> profesores = (ArrayList<tProfesores>) dal.leerNamedQuery("listaPorNombre","nombre","David");
        System.out.println("/  /  /  /  / PROFESORES /  /  /  /  /");
        for (tProfesores p :
                profesores) {
            System.out.println(p.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /");
    }
    //endregion

    //region Métodos modificar
    private static void modificarAlumnado() {

    }
    private static void modificarMatricula() {
    }

    private static void modificarAsignatura() {

    }

    private static void modificarProfesor() {

    }
    //endregion

    //region Métodos borrar
    private static void borrarAlumnado() {

    }
    private static void borrarMatricula() {
    }

    private static void borrarAsignatura() {

    }

    private static void borrarProfesor() {

    }
    //endregion
}
