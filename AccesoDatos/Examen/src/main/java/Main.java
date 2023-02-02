import EntidadesHibernate.VQ_Ganado;
import EntidadesHibernate.VQ_Naves;

import java.util.Date;

public class Main {

    private static final DAL_Hibernate dal = new DAL_Hibernate();

    public static void main(String[] args) {

        insertarGanado();
    }

    //region Métodos menú

    //endregion

    //region Métodos Insertar

    /**
     * Lee por consola los campos de una vaca y la inserta en la BBDD
     */
    private static void insertarGanado() {
        dal.insertar(leerGanadoPorConsola());
    }


    //endregion

    /*
    //region Métodos Listar

    /**
     * Muestra por pantalla todos los registros de la tabla Mesa
     */
    private static void listarMesas() {
        /*
        ArrayList<Mesa> mesas = dal.leerTodosRegistros(Mesa.class);
        System.out.println("/  /  /  /  / MESAS /  /  /  /  /");
        for (Mesa m :
                mesas) {
            System.out.println(m.toString());
        }
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /");
         */
    }

    //endregion

    //region Métodos Modificar

    /**
     * Lee por consola el id del producto pedido, si existe lee los campos nuevos por consola y lo inserta en la BBDD
     */
    private static void modificarProducto() {
        System.out.println("Introduzca el id del producto a modificar");
        /*
        Productos producto = dal.leer(Utilidades.validarEntero(), Productos.class);
        if (producto != null) {
            System.out.println("==> " + producto);
            System.out.println("Introduzca la denominación");
            producto.setDenominacion(Utilidades.leerString());
            System.out.println("Introduzca el precio");
            producto.setPrecio(BigDecimal.valueOf(Utilidades.validarFloat()));
            dal.insertar(producto);
        } else {
            System.out.println("EL id introducido no existe");
        }
        */
    }
    //endregion

    //region Métodos Borrar

    /**
     * Lee por consola el id de la mesa a borrar e intenta borrarla
     */
    private static void borrarMesa() {

        //dal.borrar();
    }
    //endregion

    //region Métodos Leer
    private static VQ_Ganado leerGanadoPorConsola() {
        Date fechaEntrada, fechaSalida;
        String nombre;
        VQ_Naves nave;
        VQ_Ganado madre;
        int idNave, idMadre;
        System.out.println("Ingrese la fecha de entrada (dd/mm/yyyy)");
        fechaEntrada = Utilidades.leerFecha();
        System.out.println("Ingrese la fecha de salida (dd/mm/yyyy)");
        fechaSalida = Utilidades.leerFecha();
        System.out.println("Ingrese el nombre");
        nombre = Utilidades.leerString();
        System.out.println("Ingrese el id de la nave");
        idNave = Utilidades.validarEntero();
        nave = dal.leer(idNave, VQ_Naves.class);
        System.out.println("Ingrese el id de la madre");
        idMadre = Utilidades.validarEntero();
        madre = dal.leer(idMadre, VQ_Ganado.class);
        return new VQ_Ganado(fechaEntrada,fechaSalida,nombre,nave,madre);
    }
    //endregion
}
