import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class Main {

    private static final String SERVIDOR = "jdbc:mysql://dns11036.phdns11.es";
    private static final String USER = "ad2223_mtirado";
    private static final String PASSWORD = "1234";
    private static final String BASE_DATOS = "ad2223_mtirado";

    public static void main(String[] args) {
        Statement st = null;
        try {
            Connection connection = conectar();

            if (connection != null) {
                st = connection.createStatement();
                int opc;
                do {
                    imprimirMenu();
                    opc = Utilidades.validarEntero(0, 4);
                    switch (opc) {
                        case 0:
                            System.out.println("Adios!");
                            break;
                        case 1:
                            crearTodasTablas(st);
                            break;
                        case 2:
                            insertarTodo(st);
                            break;
                        case 3:
                            listar(st);
                            break;
                        case 4:
                            modificar(st);
                            break;
                        case 5:
                            borrarTodasTablas(st);
                            break;
                    }
                } while (opc != 0);
                st.close();
            }

            if (st != null) {
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Función que crea y devuelve una connection al servidor,
     * los datos de acceso los obtiene de las propiedades finales de la clase
     * @return
     */
    private static Connection conectar() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(SERVIDOR, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("No se ha podido conectar a la base de datos");
            connection = null;
        } catch (ClassNotFoundException e) {
            System.out.println("Error con el jdbc driver");
            connection = null;
        }
        return connection;
    }

    /***
     * Procedimiento que crea una tabla en la base de datos definida en las propiedades finales de la clase
     * @param st Statement
     * @param tabla Nombre tabla
     * @param campos Nombre campo + tipo de dato + (si procede) Extras como AUTO_INCREMENT...
     */
    private static void crearTabla(Statement st, String tabla, String[] campos) {
        String sql = "CREATE TABLE " + BASE_DATOS + "." + tabla + "(";
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
     * Procedimiento que crea todas las tablas
     * @param st Statement
     */
    private static void crearTodasTablas(Statement st) {
        crearTabla(st, "Mesa", new String[]{"idMesa int AUTO_INCREMENT NOT NULL PRIMARY KEY", "numComensales int", "reserva tinyint"});
        crearTabla(st, "Productos", new String[]{"idProducto int NOT NULL PRIMARY KEY", "denominacion VARCHAR(45)", "precio DECIMAL(10,2)"});
        crearTabla(st, "Factura", new String[]{"idFactura int AUTO_INCREMENT NOT NULL PRIMARY KEY", "idMesa int NOT NULL", "tipoPago VARCHAR(45)", "importe DECIMAL(10,2)",
                "CONSTRAINT FK_Mesa_Factura FOREIGN KEY(idMesa) REFERENCES Mesa(idMesa)"});
        crearTabla(st, "Pedido", new String[]{"idPedido int NOT NULL PRIMARY KEY", "idFactura int NOT NULL", "idProducto int NOT NULL", "cantidad int",
                "CONSTRAINT FK_Factura_Pedido FOREIGN KEY(idFactura) REFERENCES Factura(idFactura)",
                "CONSTRAINT FK_Productos_Pedido FOREIGN KEY(idProducto) REFERENCES Productos(idProducto)"});
    }

    /***
     * Procedimiento que borra todas las tablas
     * @param st Statement
     */
    private static void borrarTodasTablas(Statement st) {
        borrarTabla(st, "Mesa");
        borrarTabla(st, "Productos");
        borrarTabla(st, "Factura");
        borrarTabla(st, "Pedido");
    }

    /***
     * Borra una tabla de la base de datos
     * @param st Statement
     * @param tabla Nombre de la tabla
     */
    private static void borrarTabla(Statement st, String tabla) {

        String sql = "DROP TABLE " + BASE_DATOS + "." + tabla;
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("La tabla " + tabla + " no esxiste");
        }
    }

    /***
     * Procedimiento que inserta los valores de un archivo de texto
     * @param st Statement
     * @param file Archivo de texto con los comandos de inserción
     */
    private static void insertar(Statement st, File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            line = br.readLine();
            while (line != null) {
                st.executeUpdate(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * Procedimiento que inserta todos los datos en las tablas
     * @param st Statement
     */
    private static void insertarTodo(Statement st) {
        insertar(st, new File("input\\inputMesa.txt"));
        insertar(st, new File("input\\inputProductos.txt"));
        insertar(st, new File("input\\inputFactura.txt"));
        insertar(st, new File("input\\inputPedido.txt"));
    }

    /***
     * Procedimiento que muestra los registros de la tabla o tablas escogida por el usuario
     * @param st Statement
     */
    private static void listar(Statement st) {
        int opc;
        System.out.println("-- -- -- -- -- -- -- -- -- --");
        System.out.println("¿Qué tabla desea listar?");
        System.out.println("1 - Mesa");
        System.out.println("2 - Factura");
        System.out.println("3 - Pedido");
        System.out.println("4 - Productos");
        System.out.println("5 - Todas");
        System.out.println("0 - Salir");
        opc = Utilidades.validarEntero(0, 5);
        switch (opc) {
            case 1:
                Listados.listarMesa(st);
                break;
            case 2:
                Listados.listarFactura(st);
                break;
            case 3:
                Listados.listarPedido(st);
                break;
            case 4:
                Listados.listarProductos(st);
                break;
            case 5:
                Listados.listarTodo(st);
                break;
        }
    }

    /***
     * Procedimiento que permite al usuario modificar un registro de la tabla que selecciones
     * @param st Statement
     */
    private static void modificar(Statement st) {
        int opc;

        System.out.println("-- -- -- -- -- -- -- -- -- --");
        System.out.println("¿Qué tabla desea modificar?");
        System.out.println("1 - Mesa");
        System.out.println("2 - Factura");
        System.out.println("3 - Pedido");
        System.out.println("4 - Productos");
        opc = Utilidades.validarEntero(1, 4);
        switch (opc) {
            case 1:
                Modificacion.modificarMesa(st);
                break;
            case 2:
                Modificacion.modificarFactura(st);
                break;
            case 3:
                Modificacion.modificarPedido(st);
                break;
            case 4:
                Modificacion.modificarProducto(st);
                break;
        }
    }

    /***
     * Procedimiento que cambia la contraseña de la base de datos
     * @param st Statement
     * @param nuevaContraseña
     */
    private static void cambiarContrasena(Statement st, String nuevaContraseña) {
        String sql = "SET PASSWORD FOR '" + "mtirado" + "' = password('" + nuevaContraseña + "')";
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Imprime el menú principal por consola
     */
    private static void imprimirMenu() {
        System.out.println("-- -- -- -- -- -- -- -- --");
        System.out.println("1 - Crear Tablas");
        System.out.println("2 - Insertar Datos");
        System.out.println("3 - Listar Datos");
        System.out.println("4 - Modificar registro");
        System.out.println("5 - Borrar Tablas");
        System.out.println("0 - Salir");
    }
}