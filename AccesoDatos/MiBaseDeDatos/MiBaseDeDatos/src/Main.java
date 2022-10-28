import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static final String SERVIDOR = "jdbc:mysql://dns11036.phdns11.es";
    private static final String USER = "mtirado";
    private static final String PASSWORD = "Remolacha";
    private static final String BASE_DATOS = "ad2223_mtirado";

    public static void main(String[] args) {
        Statement st = null;
        try {
            Connection connection = conectar();

            if (connection != null) {
                st = connection.createStatement();

                //cambiarContrasena(st, "Remolacha");

                //borrarTodasTablas(st);
                //crearTodasTablas(st);



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

    private static void crearTodasTablas(Statement st) {
        crearTabla(st, "Mesa", new String[]{"idMesa int AUTO_INCREMENT NOT NULL PRIMARY KEY", "numComensales int", "reserva tinyint"});
        crearTabla(st, "Productos", new String[]{"idProducto int NOT NULL PRIMARY KEY", "denominacion VARCHAR(45)", "precio DECIMAL(10,2)"});
        crearTabla(st, "Factura", new String[]{"idFactura int AUTO_INCREMENT NOT NULL PRIMARY KEY", "idMesa int NOT NULL", "tipoPago VARCHAR(45)", "importe DECIMAL(10,2)",
                "CONSTRAINT FK_Mesa_Factura FOREIGN KEY(idMesa) REFERENCES Mesa(idMesa)"});
        crearTabla(st, "Pedido", new String[]{"idPedido int NOT NULL PRIMARY KEY", "idFactura int NOT NULL", "idProducto int NOT NULL", "cantidad int",
                "CONSTRAINT FK_Factura_Pedido FOREIGN KEY(idFactura) REFERENCES Factura(idFactura)",
                "CONSTRAINT FK_Productos_Pedido FOREIGN KEY(idProducto) REFERENCES Productos(idProducto)"});
    }

    private static void borrarTodasTablas(Statement st) {
        borrarTabla(st, "Mesa");
        borrarTabla(st, "Productos");
        borrarTabla(st, "Factura");
        borrarTabla(st, "Pedido");
    }

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
     * @param rutaArchivo Ruta dentro del proyecto del archivo de texto con los comandos de inserción
     */
    private static void insertar(Statement st, String rutaArchivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
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

    private static void insertarTodo(Statement st) {
        insertar(st, "");
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
}