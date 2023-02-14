package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DAL_Mongo {
    private final static ConnectionString CONN_STRING = new ConnectionString("mongodb+srv://mtirado:1234@cluster0.5th56bm.mongodb.net/?retryWrites=true&w=majority");
    private final static String DATABASE_NAME = "CRUD_Restaurante";
    private final static String MESA = "Mesa", FACTURA = "Factura", PEDIDO = "Pedido", PRODUCTOS = "Productos";
    private static MongoDatabase database;

    //region Constructor
    public DAL_Mongo() {
        try {
            conexion();
        } catch (Exception e) {
            System.err.println("Error al hacer el setup al hibernate");
            throw new RuntimeException(e);
        }
    }
    //endregion

    /**
     * Lee el registro de la BBDD con el id pasado por parámetro, de no existir devuelve
     * el objeto a null y muestra un mensaje de error
     *
     * @param id id objeto deseada
     * @return Objeto deseado o objeto a null si no existe
     */
    public <T> T leer(int id, Class<T> clase) {
        /*
        T entidad = (T) clase;
        entidad = null;
        try {
            entidad = session.get(clase, id);
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer la mesa");
        }
        return entidad;
         */
        return (T) clase;
    }

    /**
     * Lee todos los registros de la BBDD y los devuelve en un arraylist con estos
     *
     * @return arraylist con los registros existentes
     */
    public <T> ArrayList<T> leerTodosRegistros(Class<T> clase) {
        /*
        ArrayList<T> registros = new ArrayList<>();
        try {
            registros = (ArrayList<T>) session.createQuery("SELECT p FROM "+clase.getName()+" p",clase).list();
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer las mesas");
        }
        return registros;
         */
        return new ArrayList<>();
    }

    /**
     * Inserta el objeto en la BBDD
     *
     * @param objeto Objeto a insertar
     */
    public void insertar(Object objeto) {
        Document doc = new Document();
        try {
            for (Field f : objeto.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                doc.append(f.getName(), f.get(objeto));
            }
            database.getCollection(objeto.getClass().getSimpleName()).insertOne(doc);
        } catch (IllegalAccessException e) {
            System.err.println("Error obteniendo un campo del objeto");
        }
    }

    /**
     * Borra el modelo pasado de la BBDD
     * @param objeto objeto a borrar
     */
    public void borrar(Object objeto) {
        /*
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(objeto);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
         */
    }

    private static void conexion() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(CONN_STRING)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }
}
