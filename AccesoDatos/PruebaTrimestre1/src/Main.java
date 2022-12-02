import Entidades.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manuel Tirado García
 */
public class Main {

    // region BBDD
    private static final String SERVIDOR = "jdbc:mysql://dns11036.phdns11.es";
    private static final String USER = "ad2223_mtirado";
    private static final String PASSWORD = "1234";
    private static final String BASE_DATOS = "ad2223_mtirado";

    private static Connection connection;
    private static Statement st;
    //endregion

    private static final String RUTA_INPUT_AGENDA = "src\\agenda.txt";

    public static void main(String[] args) {
        try {
            connection = conectar();
            if (connection != null) {
                st = connection.createStatement();
                int opc;

                crearTablas();
                insertarContactosEnBBDD(obtenerAgendaDelTxt());

                do {
                    opc = mostrarMenuYleerOpcion();
                    switch (opc) {
                        case 1:     // LLAMAR
                            llamar();
                            break;
                        case 2:     // Recibir
                            recibir();
                            break;
                        case 3:     // VER REGISTRO DE LLAMADAS
                            verRegistro();
                            break;
                        case 4:     // VER HISTORIAL ENTRANTES
                            verHistorialEntrantes();
                            break;
                        case 5:     // VER HISTORIAL SALIENTES
                            verHistorialSalientes();
                            break;
                        case 6:     // SALIR
                            System.out.println("Adios!! 👋👋");
                            break;
                    }
                } while (opc != 6);

                st.close();
                if (st != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // region Opciones Mennú
    private static void llamar() {
        Scanner sc = new Scanner(System.in);
        String numeroIntroducido;
        Timestamp fechaHoraInicio = null, fechaHoraFin = null;
        char tipo;
        System.out.println();
        System.out.println("Ingrese el número al que desea llamar");
        do {
            System.out.print("==> ");
            numeroIntroducido = sc.nextLine();
            if (numeroIntroducido.trim().length() != 9) {
                System.out.println("El número debe tener 9 cifras");
            }
        } while (numeroIntroducido.length() != 9);
        if (!contactoExisteEnBBDD(numeroIntroducido)) {
            clsContacto contacto = new clsContacto();
            System.out.println("El número " + numeroIntroducido + " no está guardado, ingrese un nombre para guardarlo");
            do {
                System.out.print("==> ");
                String nombreIntroducido = sc.nextLine();
                contacto.setNombre(nombreIntroducido);
                contacto.setTfno(numeroIntroducido);
                if (contactoExisteEnBBDD(contacto)) {
                    System.out.println("El nombre que ha introducido ya está cogido");
                }
            } while (contactoExisteEnBBDD(contacto));
            insertarContactoEnBBDD(contacto);
        }
        try {
            clsContacto contacto = leerContactoDeBBDD(numeroIntroducido);
            System.out.println("* LLamando a " + contacto.getNombre() + " *");
            Thread.sleep(1000);
            fechaHoraInicio = new Timestamp(System.currentTimeMillis());
            if (Math.random() <= 0.2) {
                System.out.println("* El número marcado no está disponible *");
                tipo = 'P';
            } else {
                System.out.println("* LLamada en curso, pulse enter para finalizar *");
                sc.nextLine();
                fechaHoraFin = new Timestamp(System.currentTimeMillis());
                System.out.println("* LLamada finalizada *");
                tipo = 'S';
                System.out.println("La llamada ha durado " + (diferenciaEnSegundos(fechaHoraInicio, fechaHoraFin) * 60) + " minutos");
            }
            insertarRegistroEnBBDD(new clsRegistro(contacto.getIdAgenda(), fechaHoraInicio, fechaHoraFin, tipo));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void recibir() {
        Scanner sc = new Scanner(System.in);
        String numeroAleatorio = "";
        char tipo = 'E';
        boolean contactoExiste;
        Timestamp fechaHoraInicio = null, fechaHoraFin = null;
        clsContacto contacto = new clsContacto();

        numeroAleatorio += "6";
        for (int i = 0; i < 8; i++) {
            numeroAleatorio += ((int) (Math.random() * 10));
        }
        contactoExiste = contactoExisteEnBBDD(numeroAleatorio);
        if (contactoExiste) {
            contacto = leerContactoDeBBDD(contacto.getIdAgenda());
            System.out.println("* Cogiendo llamada de " + contacto.getNombre() + " *");

        } else {
            System.out.println("* Cogiendo llamada de " + numeroAleatorio + " *");
        }
        try {
            Thread.sleep(1000);
            fechaHoraInicio = new Timestamp(System.currentTimeMillis());
            System.out.println("* LLamada en curso, pulse enter para finalizar *");
            sc.nextLine();
            fechaHoraFin = new Timestamp(System.currentTimeMillis());
            System.out.println("* LLamada finalizada *");
            System.out.println("La llamada ha durado " + (diferenciaEnSegundos(fechaHoraInicio, fechaHoraFin) * 60) + " minutos");
            if (!contactoExiste) {
                boolean correcto = true;
                do {
                    System.out.println("Ingrese un nombre para guardar al contacto");
                    System.out.print("==> ");
                    String nombre = sc.nextLine();
                    contacto.setNombre(nombre);
                    contacto.setTfno(numeroAleatorio);
                    if (contactoExisteEnBBDD(contacto)) {
                        System.out.println("El nombre que ha introducido ya está cogido");
                    }
                } while (contactoExisteEnBBDD(contacto));
                insertarContactoEnBBDD(contacto);
                contacto = leerContactoDeBBDD(numeroAleatorio);
            }
            insertarRegistroEnBBDD(new clsRegistro(contacto.getIdAgenda(), fechaHoraInicio, fechaHoraFin, tipo));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void verRegistro() {
        List<clsRegistro> registros = leerRegistrosDeBBDD();
        for (clsRegistro r :
                registros) {
            System.out.println(r.toString());
        }

    }

    private static void verHistorialEntrantes() {
        List<clsRegistro> registros = leerRegistrosDeBBDD('E');
        mostrarRegistrosYtiempoLlamada(registros);
    }

    private static void verHistorialSalientes() {
        List<clsRegistro> registros = leerRegistrosDeBBDD('S');
        mostrarRegistrosYtiempoLlamada(registros);
    }
// endregion

    /**
     * Muestra los registros de llamada de la BBDD junto al tiempo que ha durado cada llamada y una media de tiempo de llamada
     *
     * @param registros
     */
    private static void mostrarRegistrosYtiempoLlamada(List<clsRegistro> registros) {
        for (clsRegistro r :
                registros) {
            System.out.println(r.toString());
            System.out.println("Tiempo de llamada => " + (diferenciaEnSegundos(r.getFechaHoraInicio(), r.getFechaHoraFin()) * 60) + " minutos");
        }
        System.out.println();
        System.out.println("Tiempo medio de llamada => " + obtenerTiempoMedioLlamada(registros) + " minutos");
        System.out.println();
    }

    /***
     * Pasada una lista de registros calcula el tiempo media de estas llamadas.
     * @param registros
     * @return
     */
    private static float obtenerTiempoMedioLlamada(List<clsRegistro> registros) {
        Long tiempoMedio = 0L;
        if (registros.size() > 0) {
            for (clsRegistro r :
                    registros) {
                tiempoMedio += diferenciaEnSegundos(r.getFechaHoraInicio(), r.getFechaHoraFin());
            }
            tiempoMedio = (tiempoMedio / registros.size()) * 60;
        } else {
            tiempoMedio = 0L;
        }
        return ((float) tiempoMedio);
    }

    /**
     * Devuelve la diferencia en segundos entre 2 timestamps
     * @param t1 Tiempo Inicial
     * @param t2 Tiempo Final
     * @return
     */
    private static Long diferenciaEnSegundos(Timestamp t1, Timestamp t2) {
        return ((t2.getTime() - t1.getTime()) / 1000);
    }

    /**
     * Lee todos los registros de la BBDD y los devuelve en una lista
     * @return
     */
    private static List<clsRegistro> leerRegistrosDeBBDD() {
        List<clsRegistro> registros = new ArrayList<>();

        String sql = "SELECT * FROM " + BASE_DATOS + ".Registro;";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                clsRegistro registro = new clsRegistro();
                registro.setIdRegistro(rs.getInt(1));
                registro.setIdAgenda(rs.getInt(2));
                registro.setFechaHoraInicio(rs.getTimestamp(3));
                registro.setTipo(rs.getString(5).toCharArray()[0]);
                if (registro.getTipo() != 'P') {
                    registro.setFechaHoraFin(rs.getTimestamp(4));
                }
                registros.add(registro);
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
        return registros;
    }

    /**
     * Lee todos los registros de la BBDD en los que el tipo es igual al pasado, y los devuelve en una lista
     * @param tipo
     * @return
     */
    private static List<clsRegistro> leerRegistrosDeBBDD(char tipo) {
        List<clsRegistro> registros = new ArrayList<>();
        String sql = "SELECT * FROM " + BASE_DATOS + ".Registro WHERE tipo = '" + tipo + "';";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                clsRegistro registro = new clsRegistro();
                registro.setIdRegistro(rs.getInt(1));
                registro.setIdAgenda(rs.getInt(2));
                registro.setFechaHoraInicio(rs.getTimestamp(3));
                registro.setTipo(rs.getString(5).toCharArray()[0]);
                if (registro.getTipo() != 'P') {
                    registro.setFechaHoraFin(rs.getTimestamp(4));
                }
                registros.add(registro);
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
        return registros;
    }

    /**
     * Lee un contacto de la BBDD
     * @param telefono telefono del contacto buscado
     * @return
     */
    private static clsContacto leerContactoDeBBDD(String telefono) {
        String sql = "SELECT * FROM " + BASE_DATOS + ".Agenda WHERE tfno = '" + telefono + "';";
        return getClsContacto(sql);
    }

    /**
     * Lee un contacto de la BBDD
     * @param id id del contacto buscado
     * @return
     */
    private static clsContacto leerContactoDeBBDD(int id) {
        String sql = "SELECT * FROM " + BASE_DATOS + ".Agenda WHERE idAgenda = " + id + ";";
        return getClsContacto(sql);
    }

    /**
     * Pasada una sentencia sql, la ejecuta y obtiene los contactos y los devuelve en una lista
     * @param sql
     * @return
     */
    private static clsContacto getClsContacto(String sql) {
        try {
            ResultSet rs = st.executeQuery(sql);
            clsContacto contacto = new clsContacto();
            if (rs.next()) {
                contacto.setIdAgenda(rs.getInt(1));
                contacto.setNombre(rs.getString(2));
                contacto.setTfno(rs.getString(3));
            }
            return contacto;
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            return null;
        }
    }

    /**
     * Inserta los contactos de la lista en la BBDD
     * @param contactos
     */
    private static void insertarContactosEnBBDD(List<clsContacto> contactos) {
        for (clsContacto c :
                contactos) {
            insertarContactoEnBBDD(c);
        }
    }

    /**
     * Inserta un contacto en la BBDD, comprueba que exista
     * @param contacto
     */
    private static void insertarContactoEnBBDD(clsContacto contacto) {
        if (!contactoExisteEnBBDD(contacto)) {
            String sql = "INSERT INTO " + BASE_DATOS + ".Agenda (nombre, tfno)" +
                    " VALUES ('" + contacto.getNombre() + "', '" + contacto.getTfno() + "')";
            try {
                st.executeUpdate(sql);
            } catch (SQLException sqlException) {
                System.err.println("Error al insertar el equipo " + contacto.getNombre() + " en la BBDD");
                throw new RuntimeException(sqlException);
            }
        } else {
            System.out.println("El contacto con nombre: '" + contacto.getNombre() + "',  ya existe en la BBDD");
        }
    }

    private static void insertarRegistroEnBBDD(clsRegistro registro) {
        String sql;
        if (registro.getFechaHoraFin() == null) {
            sql = "INSERT INTO " + BASE_DATOS + ".Registro (idAgenda, fechaHoraInicio, tipo)" +
                    " VALUES (" + registro.getIdAgenda() + ", '" + registro.getFechaHoraInicio() + "', '" + registro.getTipo() + "')";
        } else {
            sql = "INSERT INTO " + BASE_DATOS + ".Registro (idAgenda, fechaHoraInicio, fechaHoraFin, tipo)" +
                    " VALUES (" + registro.getIdAgenda() + ", '" + registro.getFechaHoraInicio() + "', '" + (registro.getFechaHoraFin() + "', '" + registro.getTipo() + "');");
        }
        try {
            st.executeUpdate(sql);
        } catch (SQLException sqlException) {
            System.err.println("Error al insertar el registro en la BBDD");
            throw new RuntimeException(sqlException);
        }
    }

    private static boolean contactoExisteEnBBDD(clsContacto contacto) {
        boolean existe = false;

        String sql = "SELECT * FROM " + BASE_DATOS + ".Agenda WHERE nombre = '" + contacto.getNombre() + "' OR tfno = '" + contacto.getTfno() + "';";
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
        return existe;
    }

    private static boolean contactoExisteEnBBDD(String telefonoContacto) {
        boolean existe = false;

        String sql = "SELECT * FROM " + BASE_DATOS + ".Agenda WHERE tfno = '" + telefonoContacto + "';";
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
        return existe;
    }

    private static List<clsContacto> obtenerAgendaDelTxt() {
        List<clsContacto> contactos = new ArrayList<>();
        File input = new File(RUTA_INPUT_AGENDA);
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line = br.readLine();
            while (line != null) {
                String nombre = line;
                line = br.readLine();
                String tfno = line;
                contactos.add(new clsContacto(nombre, tfno));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error al intentar leer la agenda");
            throw new RuntimeException(e);
        }
        return contactos;
    }

    private static void crearTablas() {
        crearTabla("Agenda", new String[]{"idAgenda INT AUTO_INCREMENT PRIMARY KEY", "nombre VARCHAR(100)", "tfno VARCHAR(9)"});
        crearTabla("Registro", new String[]{"idRegistro INT AUTO_INCREMENT PRIMARY KEY", "idAgenda INT", "fechaHoraInicio TIMESTAMP", "fechaHoraFin TIMESTAMP", "tipo CHAR",
                "CONSTRAINT FK_Resgistro_Agenda FOREIGN KEY(idAgenda) REFERENCES Agenda(idAgenda)"});
    }

    /***
     * Procedimiento que crea una tabla en la base de datos definida en las propiedades finales de la clase
     * @param tabla Nombre tabla
     * @param campos Nombre campo + tipo de dato + (si procede) Extras como AUTO_INCREMENT...
     */
    private static void crearTabla(String tabla, String[] campos) {
        String sql = "CREATE TABLE IF NOT EXISTS " + BASE_DATOS + "." + tabla + "(";
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

    //region Utilidades

    /***
     * Función que crea y devuelve una connection al servidor,
     * los datos de acceso los obtiene de las propiedades finales de la clase
     * @return Connection
     */
    public static Connection conectar() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(SERVIDOR, USER, PASSWORD);
            System.out.println("Bienvenido " + USER + " 👋👋🔥");
        } catch (SQLException e) {
            System.out.println("No se ha podido conectar a la base de datos");
            connection = null;
        } catch (ClassNotFoundException e) {
            System.out.println("Error con el jdbc driver");
            connection = null;
        }
        return connection;
    }

    public static int mostrarMenuYleerOpcion() {
        int opc;
        Scanner sc = new Scanner(System.in);
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /  /  /  /");
        System.out.println("1. Llamar");
        System.out.println("2. Recibir");
        System.out.println("3. Ver registro de llamadas");
        System.out.println("4. Ver historial entrantes");
        System.out.println("5. Ver historial salientes");
        System.out.println("6. Salir del programa");
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /  /  /  /");
        do {
            System.out.print("==> ");
            opc = sc.nextInt();
            if (opc < 1 || opc > 6) {
                System.out.println("Opción fuera de rango");
            }
        } while (opc < 1 || opc > 6);
        return opc;
    }
    //endregion
}