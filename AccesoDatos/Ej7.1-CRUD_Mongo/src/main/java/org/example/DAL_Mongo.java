package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;

import javax.print.Doc;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import static com.mongodb.client.model.Filters.eq;

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
    public <T> Document leer(int id, Class<T> clase) {
        Document doc = database.getCollection(clase.getSimpleName()).find(eq(clase.getDeclaredFields()[0].getName(), id)).first();
        return doc;
    }

    /**
     * Lee todos los registros de la BBDD y los devuelve en un arraylist con estos
     *
     * @return arraylist con los registros existentes
     */
    public <T>MongoCollection<Document> leerTodosRegistros(Class<T> clase) {
        MongoCollection<Document> registro = null;
        try {
            registro = database.getCollection(clase.getSimpleName());
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer las mesas");
        }
        return registro;
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
     * @param doc documento a borrar
     * @param clase clase
     */
    public void borrar(Document doc, Class clase) {
        database.getCollection(clase.getSimpleName()).deleteOne(doc);
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
