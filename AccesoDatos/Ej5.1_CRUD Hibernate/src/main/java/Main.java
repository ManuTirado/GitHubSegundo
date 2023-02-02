import java.util.List;
import java.util.Scanner;

public class Main {

    private static DAL dal = new DAL();

    public static void main(String[] args) {
        int opc;
        do {
            mostrarContactos();
            mostrarMenu();
            opc = validarEntero(0, 4);
            switch (opc) {
                case 0 -> System.out.println("Adios 👋👋");
                case 1 -> guardarContacto();
                case 2 -> leerContacto();
                case 3 -> actualizarContacto();
                case 4 -> borrarContacto();
            }
        } while (opc != 0);
    }

    /**
     * Muestra todos los contactos por consola
     */
    private static void mostrarContactos() {
        List<AgendaEntity> contactos = dal.leerTodos();
        System.out.println("--------------------------------------");
        for (AgendaEntity c :
                contactos) {
            System.out.println(c.getIdAgenda() + " -> " + c.getNombre() + " | " + c.getTfno());
        }
        System.out.println("--------------------------------------");
    }

    /**
     * Pide al usuario los datos para guardar un nuevo contacto
     */
    private static void guardarContacto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del contacto a guardar");
        System.out.print("=> ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el teléfono del contacto a guardar");
        System.out.print("=> ");
        String telefono = sc.nextLine();

        dal.guardar(nombre, telefono);
    }

    /**
     * Pide al usuario un id para leer un contacto
     */
    private static void leerContacto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del contacto a leer");
        System.out.print("=> ");
        int id = sc.nextInt();

        AgendaEntity contacto = dal.leer(id);
        if (contacto != null) {
            System.out.println("Id => " + contacto.getIdAgenda());
            System.out.println("Nombre => " + contacto.getNombre());
            System.out.println("Teléfono => " + contacto.getTfno());
        }
    }

    /**
     * Pide al usuario un id y los nuevos nombre y teléfono para actualizar el contacto
     */
    private static void actualizarContacto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del contacto a actualizar");
        System.out.print("=> ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese el nuevo nombre del contacto");
        System.out.print("=> ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el nuevo teléfono del contacto");
        System.out.print("=> ");
        String telefono = sc.nextLine();

        dal.actualizar(id,nombre,telefono);
    }

    /**
     * Pide al usuario un id para borrar el contacto correspondiente
     */
    private static void borrarContacto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del contacto a borrar");
        System.out.print("=> ");
        int id = sc.nextInt();

        dal.borrar(id);
    }

    //region Utilidades

    /**
     * Valida la entrada de un entero en el rango dado, ambos incluidos
     * Precondiciones: min<=max
     * @param min mínimo incluido
     * @param max máximo incluido
     * @return entero validado en el rango dado
     */
    private static int validarEntero(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int opc = min - 1;
        do {
            try {
                System.out.print("=> ");
                opc = sc.nextInt();
                if (opc < min || opc > max) {
                    System.out.println("Valor fuera de rango");
                }
            } catch (Exception e) {
                sc.next();
                System.out.println("Valor no válido");
            }
        } while (opc < min || opc > max);
        return opc;
    }

    /**
     * Muestra el menú del CRUD con Hibernate
     */
    private static void mostrarMenu() {
        System.out.println("- - - - - - - - - CRUD Hibernate - - - - - - - - -");
        System.out.println("1 - Guardar contacto");
        System.out.println("2 - Leer contacto");
        System.out.println("3 - Actualizar contacto");
        System.out.println("4 - Borrar contacto");
        System.out.println("0 - Salir");
    }
    //endregion
}
