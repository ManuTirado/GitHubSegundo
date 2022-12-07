import Entidades.clsEquipo;
import Entidades.clsPartido;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Main {

    public static final String BASE_DATOS = "ad2223_mtirado";
    private static Connection connection;
    public static Statement st;
    private static final String RUTA_INPUT_EQUIPOS = "src\\equipos.txt";
    private static ArrayList<clsEquipo> equipos = new ArrayList<>();

    private static int velocidadPartido = 10;     // Tiempo que dura cada minuto de partido en millis

    public static void main(String[] args) {
        try {
            connection = Conexion.conectar();
            if (connection != null) {
                st = connection.createStatement();
                crearTablas();

                if (hayRegistrosEnBBDD("Equipos")) {
                    equipos = Listados.leerEquiposDeLaBBDD();
                } else {
                    reiniciar();
                }

                int opc;
                do {
                    opc = mostrarMenuYvalidarOpc();
                    switch (opc) {
                        case 1 -> iniciarOctavos();
                        case 2 -> iniciarCuartos();
                        case 3 -> iniciarSemifinales();
                        case 4 -> iniciarFinal();
                        case 5 -> reiniciar();
                        case 6 -> System.out.println("Adios!! 👋👋");
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


    //region Menú Principal
    /**
     * En caso de que no se hayan jugado los octavos, se escogen 16 equipos aleatoriamente de equipos, después
     * estos equipos se emparejan y se les enfrenta en un partido, después del cual se actualiza la información de ambos
     * equipos en la tabla Equipos. Una vez terminados los partidos, se procede a insertarlos en la tabla de octavos.
     *
     * En caso de que ya se hayan jugado, se muestran los resultados.
     */
    private static void iniciarOctavos() {
        if (!hayRegistrosEnBBDD("Octavos")) {
            List<clsEquipo> equiposQueJugaran = new ArrayList<>();
            List<clsPartido> partidosJugados = new ArrayList<>();
            do {      // Escojo 16 equipos aleatoriamente
                int aleatorio = (int) (Math.random() * equipos.size());
                if (!equiposQueJugaran.contains(equipos.get(aleatorio))) {
                    equiposQueJugaran.add(equipos.get(aleatorio));
                }
            } while (equiposQueJugaran.size() < 16);
            for (int i = 0; i < 8; i++) {
                System.out.println("- - - - - Partido " + (i + 1) + " de los octavos - - - - -");
                System.out.println();
                clsPartido partido = jugarPartido(
                        equiposQueJugaran.get(i * 2).getIdEquipo(),
                        equiposQueJugaran.get(i * 2 + 1).getIdEquipo());
                Modificaciones.actualizarEquipos(partido);
                partidosJugados.add(partido);
            }
            for (clsPartido p :
                    partidosJugados) {
                Manejadora.insetarPartidoEnBBDD("Octavos", p);
            }
        } else {
            mostrarResultados("Octavos");
        }
    }

    /**
     * En caso de que se hayan jugado los octavos y no se hayan jugado los cuartos, se coge a los ganadores de los octavos, después
     * estos equipos se emparejan y se les enfrenta en un partido, después del cual se actualiza la información de ambos
     * equipos en la tabla Equipos. Una vez terminados los partidos, se procede a insertarlos en la tabla de cuartos.
     *
     * En caso de que ya se hayan jugado, se muestran los resultados.
     *
     * En caso de que no se hayan jugado los octavos, se informa al usuario y no se hace nada
     */
    private static void iniciarCuartos() {
        if (hayRegistrosEnBBDD("Octavos")) {
            if (!hayRegistrosEnBBDD("Cuartos")) {
                List<clsEquipo> equiposQueJugaran = Listados.obtenerGanadoresDeBBDD("Octavos");
                List<clsPartido> partidosJugados = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    System.out.println("- - - - - Partido " + (i + 1) + " de los cuartos - - - - -");
                    System.out.println();
                    clsPartido partido = jugarPartido(
                            equiposQueJugaran.get(i * 2).getIdEquipo(),
                            equiposQueJugaran.get(i * 2 + 1).getIdEquipo());
                    Modificaciones.actualizarEquipos(partido);
                    partidosJugados.add(partido);
                }
                for (clsPartido p :
                        partidosJugados) {
                    Manejadora.insetarPartidoEnBBDD("Cuartos", p);
                }
            } else {
                mostrarResultados("Cuartos");
            }
        } else {
            System.out.println("Todavía no se han jugado los octavos");
        }
    }

    /**
     * En caso de que se hayan jugado los cuartos y no se hayan jugado las semifinales, se coge a los ganadores de los cuartos, después
     * estos equipos se emparejan y se les enfrenta en un partido, después del cual se actualiza la información de ambos
     * equipos en la tabla Equipos. Una vez terminados los partidos, se procede a insertarlos en la tabla de semifinales.
     *
     * En caso de que ya se hayan jugado, se muestran los resultados.
     *
     * En caso de que no se hayan jugado los cuartos, se informa al usuario y no se hace nada
     */
    private static void iniciarSemifinales() {
        if (hayRegistrosEnBBDD("Cuartos")) {
            if (!hayRegistrosEnBBDD("Semifinales")) {
                List<clsEquipo> equiposQueJugaran = Listados.obtenerGanadoresDeBBDD("Cuartos");
                List<clsPartido> partidosJugados = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    System.out.println("- - - - - Partido " + (i + 1) + " de la semifinal - - - - -");
                    System.out.println();
                    clsPartido partido = jugarPartido(
                            equiposQueJugaran.get(i * 2).getIdEquipo(),
                            equiposQueJugaran.get(i * 2 + 1).getIdEquipo());
                    Modificaciones.actualizarEquipos(partido);
                    partidosJugados.add(partido);
                }
                for (clsPartido p :
                        partidosJugados) {
                    Manejadora.insetarPartidoEnBBDD("Semifinales", p);
                }
            } else {
                mostrarResultados("Semifinales");
            }
        } else {
            System.out.println("Todavía no se han jugado los cuartos");
        }
    }

    /**
     * En caso de que se hayan jugado las semifinales y no se haya jugado la final, se coge a los ganadores de las semifinales, después
     * estos dos equipos se emparejan y se les enfrenta en un partido, después del cual se actualiza la información de ambos
     * equipos en la tabla Equipos. Una vez terminado el partido, se procede a insertarlo en la tabla de final.
     *
     * En caso de que ya se hayan jugado, se muestran los resultados.
     *
     * En caso de que no se hayan jugado las semifinales, se informa al usuario y no se hace nada
     */
    private static void iniciarFinal() {
        if (hayRegistrosEnBBDD("Semifinales")) {
            if (!hayRegistrosEnBBDD("Final")) {
                List<clsEquipo> equiposQueJugaran = Listados.obtenerGanadoresDeBBDD("Semifinales");
                System.out.println("⚽ ⚽ ⚽ ⚽ ⚽ FINAL! ⚽ ⚽ ⚽ ⚽ ⚽");
                System.out.println();
                clsPartido partido = jugarPartido(
                        equiposQueJugaran.get(0).getIdEquipo(),
                        equiposQueJugaran.get(1).getIdEquipo());
                Modificaciones.actualizarEquipos(partido);
                Manejadora.insetarPartidoEnBBDD("Final", partido);
            } else {
                mostrarResultados("Final");
            }
        } else {
            System.out.println("Todavía no se han jugado las semifinales");
        }
    }

    /**
     * Reinicia el campeonato, vacia las tablas, lee los equipos del txt y los inserta en la BBDD
     */
    private static void reiniciar() {
        vaciarTablas();
        obtenerEquiposDelTxt();
        insertarEquiposEnBBDD();
    }

    //endregion

    /**
     * Juega un partido entre dos equipos y devuelve un objeto partido con los resultados del partido.
     * Solo puede ganar 1, al tratarse de un partido eliminatorio para una clasificación, el partido no acaba hasta que
     * uno gane. Para que no se haga eterno, cada minuto de prórroga aumenta la probabilidad de obtener una
     * mayor posesión del balón.
     * @param equipo1 nombre del equipo 1
     * @param equipo2 nombre del equipo 2
     * @return Nombre del equipo ganador o null si hay empate
     */
    public static clsPartido jugarPartido(String equipo1, String equipo2) {
        int maxDiferencia = 10;
        int tiempo = 0, tiempoMax, golesEquipo1 = 0, golesEquipo2 = 0;
        boolean terminado = false;
        int posesionEquipo1 = 50;
        int posesionEquipo2 = 50;
        tiempoMax = (int) (Math.random() * 11 + 90);
        do {
            try {
                System.out.print("Min " + tiempo + " => ");
                Thread.sleep(velocidadPartido);
                int diferenciaAzar = ((int) (Math.random() * maxDiferencia + 1)) - ((int) (Math.random() * maxDiferencia + 1));   // Equipo1 - Equipo2
                posesionEquipo1 += diferenciaAzar;
                posesionEquipo2 -= diferenciaAzar;
                System.out.println(equipo1 + " => " + posesionEquipo1 + "%        " + equipo2 + " => " + posesionEquipo2 + "%");
                if (posesionEquipo1 > 95) {
                    System.out.println("GOOOOL!! de " + equipo1 + " en el minuto " + tiempo);
                    golesEquipo1++;
                    posesionEquipo1 = 50;
                    posesionEquipo2 = 50;
                }
                if (posesionEquipo2 > 95) {
                    System.out.println("GOOOOL!! de " + equipo2 + " en el minuto " + tiempo);
                    golesEquipo2++;
                    posesionEquipo1 = 50;
                    posesionEquipo2 = 50;
                }
                tiempo++;
                if (tiempo > tiempoMax) {
                    if ((golesEquipo1 != golesEquipo2)) {
                        System.out.println("Partido terminado!");
                        System.out.println(equipo1 + " " + golesEquipo1 + " : " + golesEquipo2 + " " + equipo2);
                        terminado = true;
                    } else {
                        System.out.print("(Prórroga) ");
                        maxDiferencia *= 1.1;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (!terminado);
        return (new clsPartido(equipo1, equipo2, golesEquipo1, golesEquipo2));
    }

    //region Métodos

    /**
     * Imprime por consola los resultados de una tabla
     * @param tabla Tabla de la cual queremos mostrar los resultados
     */
    private static void mostrarResultados(String tabla) {
        ArrayList<clsPartido> partidos = Listados.obtenerResultados(tabla);
        for (clsPartido p :
                partidos) {
            System.out.println(p.toString());
        }
    }

    /**
     * Devuelve true si en la tabla pasada hay al menos 1 registro.
     * @param tabla Tabla en la que queremos comprobar si hay algún registro
     * @return true si hay algún registro
     */
    private static boolean hayRegistrosEnBBDD(String tabla) {
        String sql = "SELECT * FROM " + BASE_DATOS + "." + tabla + ";";
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
        return false;
    }

    /**
     * Inserta los equipos de equipos en la base de datos Equipos
     */
    private static void insertarEquiposEnBBDD() {
        for (clsEquipo e :
                equipos) {
            Manejadora.insertarEquipo(e.getIdEquipo());
        }
    }

    /**
     * Vacia de contenido todas las tablas
     */
    private static void vaciarTablas() {
        Modificaciones.vaciarTabla("Equipos");
        Modificaciones.vaciarTabla("Octavos");
        Modificaciones.vaciarTabla("Cuartos");
        Modificaciones.vaciarTabla("Semifinales");
        Modificaciones.vaciarTabla("Final");
    }

    /**
     * Crea las tablas necesarias para el funcionamiento del programa, solo si estas no están ya creadas.
     */
    private static void crearTablas() {
        Modificaciones.crearTabla("Equipos", "idEquipo VARCHAR(25) PRIMARY KEY, ganados INT, empatados INT, perdidos INT, golesMarcados INT, golesRecibidos INT".split(","));
        Modificaciones.crearTabla("Octavos", new String[]{"equipoA VARCHAR(25)", "equipoB VARCHAR(25)", "golesA INT", "golesB INT"});
        Modificaciones.crearTabla("Cuartos", new String[]{"equipoA VARCHAR(25)", "equipoB VARCHAR(25)", "golesA INT", "golesB INT"});
        Modificaciones.crearTabla("Semifinales", new String[]{"equipoA VARCHAR(25)", "equipoB VARCHAR(25)", "golesA INT", "golesB INT"});
        Modificaciones.crearTabla("Final", new String[]{"equipoA VARCHAR(25)", "equipoB VARCHAR(25)", "golesA INT", "golesB INT"});
    }
    //endregion

    //region Utilidades

    /**
     * Lee todos los equipos del txt y los añade al listado de equipos, habiendo vaciado el listado con anterioridad.
     */
    private static void obtenerEquiposDelTxt() {
        equipos.clear();
        File input = new File(RUTA_INPUT_EQUIPOS);
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line = br.readLine();
            while (line != null) {
                equipos.add(new clsEquipo(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error al intentar leer los equipos");
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra el menú principal y lee la entrada del usuario por consola,
     * valida que el número introducido esté comprendido entre las opciones disponibles.
     * Precondiciones: El usuario introduce un entero
     * @return opción validada en el rango del menú
     */
    public static int mostrarMenuYvalidarOpc() {
        Scanner sc = new Scanner(System.in);
        String s = "/  /  /  /  /  /  /  /  /  /  /  /  /  /  /  / /" + System.lineSeparator() +
                "/ / / / / MUNDIAL RONALDINHO SOCCER 64 / / / / /" + System.lineSeparator() +
                "/  /  /  /  /  /  /  /  /  /  /  /  /  /  /  / /" + System.lineSeparator() +
                "1. Iniciar Octavos" + System.lineSeparator() +
                "2. Iniciar Cuartos" + System.lineSeparator() +
                "3. Iniciar Semifinales" + System.lineSeparator() +
                "4. Iniciar la Final" + System.lineSeparator() +
                "5. Reiniciar" + System.lineSeparator() +
                "6. Salir" + System.lineSeparator() +
                "/  /  /  /  /  /  /  /  /  /  /  /  /  /  /  / /" + System.lineSeparator();
        System.out.println(s);
        int opc;
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