import java.sql.*;
import java.util.Scanner;

public class Main {

    private static String servidor = "jdbc:mysql://dns11036.phdns11.es";
    private static String user = "ad2223_mtirado";
    private static String password = "1234";
    private static String baseDatos = "ad2223_mtirado";

    private static Connection connection;
    private static Statement st;

    public static void main(String[] args) {
        //login();

        try {
            connection = conectar();
            if (connection != null) {
                st = connection.createStatement();

                crearTabla("Contactos", new String[]{"IdContacto int PRIMARY KEY AUTO_INCREMENT", "NombreContacto varchar(30) NOT NULL", "IsBloqueado bit NOT NULL"});
                crearTabla("Mensajes", new String[]{"IdMensaje int PRIMARY KEY auto_increment", "Origen varchar(30) NOT NULL", "Destino varchar(30) NOT NULL",
                        "FechaHora timestamp NOT NULL", "Texto text NOT NULL", "IsLeido bit", "IsRecibido bit", "IdContacto int NOT NULL", "Foreign Key(IdContacto)references Contactos(IdContacto)"});

                int opc;
                do {
                    mostrarMenuPrincipal();
                    opc = Utilidades.validarOpcion(0,3);
                    switch (opc) {
                        case 1:     // Contactos
                            contactos();
                            break;
                        case 2:     // Añadir Contacto
                            anadirContacto();
                            break;
                        case 3:     // Bloquear/Desbloquear Contacto
                            bloquearDesbloquearContacto();
                            break;
                        case 0:     // Salir
                            System.out.println("Adios!");
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



    private static void contactos() {
        try {
            ResultSet rs = obtenerContactosSinBloquear();
            System.out.println("IdContacto    Nombre");
            while(rs.next()){
                System.out.println();
                System.out.println("     " + rs.getString("IdContacto") + "        " + rs.getString("NombreContacto"));
            }
            System.out.println();
            System.out.println("Escoja un contacto por el id: ");
            int id = Utilidades.validarEntero();
            abrirChat(id);

        } catch (SQLException sqlException) {
            System.err.println("Error del sql al intentar mostrar los contactos");
        }
    }

    private static void abrirChat(int id) {

    }

    private static void mostrarContactos(ResultSet rs) {
        try {
            System.out.println("IdContacto    Nombre");
            while(rs.next()){
                System.out.println();
                System.out.println("     " + rs.getString("IdContacto") + "        " + rs.getString("NombreContacto"));
            }
            System.out.println();
        } catch (SQLException sqlException) {
            System.err.println("Error del sql al intentar mostrar los contactos");
        }
    }

    private static ResultSet obtenerTodosContactos() {
        ResultSet rs = null;
        String sql = "SELECT IdContacto, NombreContacto FROM "+ baseDatos +".Contactos";
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException sqlException) {
            System.err.println("Error del sql al intentar obtener los contactos");
        }
        return rs;
    }

    private static ResultSet obtenerContactosSinBloquear() {
        ResultSet rs = null;
        String sql = "SELECT IdContacto, NombreContacto FROM "+ baseDatos +".Contactos WHERE IsBloqueado = 0";
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException sqlException) {
            System.err.println("Error del sql al intentar obtener los contactos");
        }
        return rs;
    }

    private static void bloquearDesbloquearContacto() {
        Scanner sc = new Scanner(System.in);
        int idContacto, isBloqueado = 0;
        String sql = null, sqlQuery, nombreContacto = null;

        mostrarContactos(obtenerTodosContactos());

        System.out.println("Diga el id del contacto que quiere bloquear: ");
        idContacto = sc.nextInt();

        sqlQuery = "SELECT IsBloqueado FROM " + baseDatos +".Contactos WHERE IdContacto= "+ idContacto+";";
        try {
           /* ResultSet resultSet = obtenerTodosContactos();
            nombreContacto = resultSet.getString("NombreContacto");*/
            ResultSet rs = st.executeQuery(sqlQuery);
            if (rs.next()) {
                isBloqueado = Integer.parseInt(rs.getString("IsBloqueado"));
            }
            if (isBloqueado == 0){
                sql = "UPDATE " + baseDatos + ".Contactos SET IsBloqueado = 1 WHERE IdContacto= "+ idContacto+";";
            }else{
                sql = "UPDATE " + baseDatos + ".Contactos SET IsBloqueado = 0 WHERE IdContacto= "+ idContacto+";";
                /*sql="GRANT INSERT " +
                        "ON " + baseDatos + ".* " +
                        "TO '"+nombreContacto+"'@'localHost';";
                st.execute(sql);*/
            }
            st.executeUpdate(sql);
            System.out.println("Se ha actualizado con éxito :D");
        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error muy grave :(");
        }

    }

    private static void anadirContacto() {
        String nombreContacto;
        Scanner sc = new Scanner(System.in);

        System.out.println("    Nombre del contacto que quiere añadir:");
        System.out.print("==> ");
        nombreContacto = sc.nextLine();

        String sql = "INSERT INTO " + baseDatos + ".Contactos (NombreContacto, IsBloqueado) values ('"+nombreContacto+"', 0);";
        try {
            st.executeUpdate(sql);

            sql="GRANT INSERT " +
                    "ON " + baseDatos + ".* " +
                    "TO '"+nombreContacto+"'@'localHost';";
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("- - - - - - " + user + " - - - - - -");
        System.out.println("1 - Contactos");
        System.out.println("2 - Añadir Contacto");
        System.out.println("3 - Bloquear Contacto");
        System.out.println("0 - Salir");
        System.out.println();
    }

    private static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("- - - - - - WhatsApp2 - - - - - -");
        System.out.println();
        System.out.println("    Usuario:");
        System.out.print("==> ");
        user = sc.nextLine();
        System.out.println("    Contraseña:");
        System.out.print("==> ");
        password = sc.nextLine();
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
            connection = DriverManager.getConnection(servidor, user, password);
            System.out.println("Bienvenido " + user + " 👋👋🔥");
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
     * @param tabla Nombre tabla
     * @param campos Nombre campo + tipo de dato + (si procede) Extras como AUTO_INCREMENT...
     */
    private static void crearTabla(String tabla, String[] campos) {
        String sql = "CREATE TABLE IF NOT EXISTS " + baseDatos + "." + tabla + "(";
        sql += campos[0];
        for (int i = 1; i < campos.length; i++) {
            sql += "," + campos[i];
        }
        sql += " )";
        //System.out.println(sql);
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}