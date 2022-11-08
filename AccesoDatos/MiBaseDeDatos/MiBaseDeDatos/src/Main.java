import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

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

                //insertarTodo(st);

                //listarTodo(st);

                modificar(st);

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

    private static void insertarTodo(Statement st) {
        insertar(st, new File("input\\inputMesa.txt"));
        insertar(st, new File("input\\inputProductos.txt"));
        insertar(st, new File("input\\inputFactura.txt"));
        insertar(st, new File("input\\inputPedido.txt"));
    }

    private static void listarMesa(Statement st) {
        System.out.println("Registros de Mesa:");
        String sql = "SELECT * FROM ad2223_mtirado.Mesa";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println("IdMesa:" + rs.getInt("idMesa") + ", numComensales:" + rs.getInt("numComensales") + ", reserva:" + rs.getInt("reserva"));
        } catch (SQLException e) {
            System.out.println("Error en el Select de Mesa");
        }
    }

    private static void listarFactura(Statement st) {
        System.out.println("Registros de Factura:");
        String sql = "SELECT * FROM ad2223_mtirado.Factura";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println("idFactura:" + rs.getInt("idFactura") + ", idMesa:" + rs.getInt("idMesa") + ", tipoPago:" + rs.getString("tipoPago") + ", importe:" + rs.getFloat("importe"));
        } catch (SQLException e) {
            System.out.println("Error en el Select de Factura");
        }
    }

    private static void listarPedido(Statement st) {
        System.out.println("Registros de Pedido:");
        String sql = "SELECT * FROM ad2223_mtirado.Pedido";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println("idPedido:" + rs.getInt("idPedido") + ", idFactura:" + rs.getInt("idFactura") + ", idProducto:" + rs.getInt("idProducto") + ", cantidad:" + rs.getInt("cantidad"));
        } catch (SQLException e) {
            System.out.println("Error en el Select de Factura");
        }
    }

    private static void listarProductos(Statement st) {
        System.out.println("Registros de Productos:");
        String sql = "SELECT * FROM ad2223_mtirado.Productos";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println(rs.getInt("idProducto") + " " + rs.getString("denominacion") + " " + rs.getFloat("precio"));
        } catch (SQLException e) {
            System.out.println("Error en el Select de Factura");
        }
    }

    private static void listarTodo(Statement st) {
        listarMesa(st);
        listarFactura(st);
        listarPedido(st);
        listarProductos(st);
    }


    private static void modificar(Statement st) {
        int opc;

        System.out.println("-- -- -- -- -- -- -- -- -- --");
        System.out.println("¿Qué tabla desea modificar?");
        System.out.println("1 - Mesa");
        System.out.println("2 - Factura");
        System.out.println("3 - Pedido");
        System.out.println("4 - Productos");
        opc = validarEntero(1, 4);
        switch (opc) {
            case 1:
                modificarMesa(st);
                break;
            case 2:
                modificarFactura(st);
                break;
            case 3:
                modificarPedido(st);
                break;
            case 4:
                modificarProducto(st);
                break;
        }
    }

    private static void modificarMesa(Statement st) {
        int mesaSeleccionada, numComensales, reserva;
        String sql;

        try {
            sql = "SELECT * FROM ad2223_mtirado.Mesa ORDER BY idMesa ASC LIMIT 1";
            imprimirMesa(st, sql);

            System.out.println("...");

            sql = "SELECT * FROM ad2223_mtirado.Mesa ORDER BY idMesa DESC LIMIT 1";
            imprimirMesa(st, sql);


            System.out.println();
            System.out.println("Seleccione el id de la Mesa que desea modificar:");
            mesaSeleccionada = validarEntero();

            sql = "SELECT * FROM ad2223_mtirado.Mesa WHERE idMesa="+mesaSeleccionada;
            imprimirMesa(st, sql);

            System.out.println("Modificación:");
            System.out.print("NumComensales ");
            numComensales = validarEntero();
            System.out.print("Reserva ");
            reserva = validarEntero();

            sql = "UPDATE ad2223_mtirado.Mesa SET numComensales ="+numComensales+", reserva="+reserva+" WHERE idMesa ="+mesaSeleccionada+";";
            st.executeUpdate(sql);
            sql = "SELECT * FROM ad2223_mtirado.Mesa WHERE idMesa="+mesaSeleccionada;
            imprimirMesa(st, sql);

        } catch (SQLException e) {
            System.out.println("Error en el SQL");
        }

    }

    private static void imprimirMesa(Statement st, String sql) {
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("IdMesa:" + rs.getInt("idMesa") + ", numComensales:" + rs.getInt("numComensales") + ", reserva:" + rs.getInt("reserva"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void modificarFactura(Statement st) {
        int facturaSeleccionada, idMesa, tipoPago;
        float importe;
        String tipoPagoStr;
        String sql;

        try {
            sql = "SELECT * FROM ad2223_mtirado.Factura ORDER BY idFactura ASC LIMIT 1";
            imprimirFactura(st, sql);

            System.out.println("...");

            sql = "SELECT * FROM ad2223_mtirado.Factura ORDER BY idFactura DESC LIMIT 1";
            imprimirFactura(st, sql);


            System.out.println();
            System.out.println("Seleccione el id de la Mesa que desea modificar:");
            facturaSeleccionada = validarEntero();

            sql = "SELECT * FROM ad2223_mtirado.Factura WHERE idFactura="+facturaSeleccionada;
            imprimirFactura(st, sql);

            System.out.println("Modificación:");
            System.out.print("IdMesa ");
            idMesa = validarEntero();
            System.out.print("TipoPago (1.Efectivo - 2.Tarjeta) ");
            tipoPago = validarEntero(1,2);
            if (tipoPago==1) {
                tipoPagoStr = "efectivo";
            } else {
                tipoPagoStr = "tarjeta";
            }
            System.out.print("Importe ");
            importe = validarFloat();

            sql = "UPDATE ad2223_mtirado.Factura SET idMesa ="+idMesa+", tipoPago='"+ tipoPagoStr +"', importe="+ importe +" WHERE idFactura ="+facturaSeleccionada+";";
            st.executeUpdate(sql);
            sql = "SELECT * FROM ad2223_mtirado.Factura WHERE idFactura="+facturaSeleccionada;
            imprimirFactura(st, sql);
        } catch (SQLException e) {
            System.out.println("Error en el SQL");
        }

    }

    private static void imprimirFactura(Statement st, String sql) {
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("idFactura:" + rs.getInt("idFactura") + ", idMesa:" + rs.getInt("idMesa") + ", tipoPago:" + rs.getString("tipoPago") + ", importe:" + rs.getFloat("importe"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void modificarPedido(Statement st) {
        int idPedido, idFactura, idProducto, cantidad;
        String sql;

        try {
            sql = "SELECT * FROM ad2223_mtirado.Pedido ORDER BY idPedido ASC LIMIT 1";
            imprimirPedido(st, sql);

            System.out.println("...");

            sql = "SELECT * FROM ad2223_mtirado.Pedido ORDER BY idPedido DESC LIMIT 1";
            imprimirPedido(st, sql);


            System.out.println();
            System.out.println("Seleccione el id del pedido que desea modificar:");
            idPedido = validarEntero();

            sql = "SELECT * FROM ad2223_mtirado.Pedido WHERE idPedido="+idPedido;
            imprimirPedido(st, sql);

            System.out.println("Modificación:");
            System.out.print("idFactura ");
            idFactura = validarEntero();
            System.out.print("idProducto ");
            idProducto = validarEntero();
            System.out.print("Cantidad ");
            cantidad = validarEntero();

            sql = "UPDATE ad2223_mtirado.Pedido SET idFactura ="+idFactura+", idProducto="+idProducto+", cantidad="+cantidad+" WHERE idPedido ="+idPedido+";";
            st.executeUpdate(sql);
            sql = "SELECT * FROM ad2223_mtirado.Pedido WHERE idPedido="+idPedido;
            imprimirPedido(st, sql);

        } catch (SQLException e) {
            System.out.println("Error en el SQL");
        }

    }

    private static void imprimirPedido(Statement st, String sql) {
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("IdPedido:" + rs.getInt("idPedido") + ", idFactura:" + rs.getInt("idFactura") + ", idProducto:" + rs.getInt("idProducto") + ", cantidad:" + rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void modificarProducto(Statement st) {
        int idProducto;
        String denominacion;
        float precio;
        String sql;

        try {
            sql = "SELECT * FROM ad2223_mtirado.Productos ORDER BY idProducto ASC LIMIT 1";
            imprimirProducto(st, sql);

            System.out.println("...");

            sql = "SELECT * FROM ad2223_mtirado.Productos ORDER BY idProducto DESC LIMIT 1";
            imprimirProducto(st, sql);


            System.out.println();
            System.out.println("Seleccione el id del producto que desea modificar:");
            idProducto = validarEntero();

            sql = "SELECT * FROM ad2223_mtirado.Productos WHERE idProducto="+idProducto;
            imprimirProducto(st, sql);

            System.out.println("Modificación:");
            System.out.print("denominación ");
            denominacion = new Scanner(System.in).nextLine();
            System.out.print("Precio ");
            precio = validarFloat();

            sql = "UPDATE ad2223_mtirado.Productos SET denominacion ='"+denominacion+"', precio="+precio+" WHERE idProducto ="+idProducto+";";
            st.executeUpdate(sql);
            sql = "SELECT * FROM ad2223_mtirado.Productos WHERE idProducto="+idProducto;
            imprimirProducto(st, sql);

        } catch (SQLException e) {
            System.out.println("Error en el SQL");
        }

    }

    private static void imprimirProducto(Statement st, String sql) {
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("IdProducto:" + rs.getInt("idProducto") + ", denominación:" + rs.getString("denominacion") + ", precio:" + rs.getFloat("precio"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int validarEntero(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int numero;
        do {
            try {
                System.out.print("==> ");
                numero = sc.nextInt();
                if (numero < min || numero > max) {
                    System.out.println("Número fuera del rango, introduzca un entero entre " + min + " y " + max);
                }
            } catch (Exception e) {
                System.out.println("Valor no válido");
                // Le asigno al número un valor fuera del rango para que no salga del bucles
                numero = min - 1;
                sc.nextLine();
            }
        } while (numero < min || numero > max);
        return numero;
    }
    private static int validarEntero() {
        Scanner sc = new Scanner(System.in);
        int numero=0;
        boolean correcto=false;
        do {
            try {
                System.out.print("==> ");
                numero = sc.nextInt();
                correcto=true;
            } catch (Exception e) {
                System.out.println("Valor no válido");
                sc.nextLine();
            }
        } while (!correcto);
        return numero;
    }

    private static float validarFloat() {
        Scanner sc = new Scanner(System.in);
        float numero=0;
        boolean correcto=false;
        do {
            try {
                System.out.print("==> ");
                numero = sc.nextFloat();
                correcto=true;
            } catch (Exception e) {
                System.out.println("Valor no válido");
                sc.nextLine();
            }
        } while (!correcto);
        return numero;
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

    private static String obtener2decimales (float num) {
        return String.format("%.2f", num);
    }
}