package org.example;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;

import java.lang.reflect.Field;

import static com.mongodb.client.model.Filters.eq;

public class DAL_Mongo {
    private final static ConnectionString CONN_STRING = new ConnectionString("mongodb+srv://mtirado:1234@cluster0.5th56bm.mongodb.net/?retryWrites=true&w=majority");
    private final static String DATABASE_NAME = "CRUD_Restaurante";
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
     * Lee el registro de la BBDD con el ID pasado por parámetro, de no existir devuelve
     * el objeto a null y muestra un mensaje de error.
     * @param id ID del registro a leer
     * @param clase clase del objeto a leer
     * @return Objeto deseado o objeto a null si no existe
     */
    public <T> Document leer(int id, Class<T> clase) {
        Document doc = database.getCollection(clase.getSimpleName()).find(eq(clase.getDeclaredFields()[0].getName(), id)).first();
        return doc;
    }

    /**
     * Lee todos los registros de la BBDD y los devuelve en un documento de mongoDB.
     * @param clase clase del objeto a leer
     * @return Documento con todos los registros de la clase pasada o null si no hay registros
     */
    public <T> MongoCollection<Document> leerTodosRegistros(Class<T> clase) {
        MongoCollection<Document> registro = null;
        try {
            registro = database.getCollection(clase.getSimpleName());
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer las mesas");
        }
        return registro;
    }

    /**
     * Lee todos los registros de la BBDD y los devuelve en un findIterable de mongoDB.
     * @param clase clase del objeto a leer
     * @param campo campo por el que filtrar
     * @param valor valor del campo por el que filtrar
     * @return FindIterable con todos los registros de la clase pasada o null si no hay registros
     */
    public <T> FindIterable<Document> leerTodosRegistros(Class<T> clase, String campo, int valor) {
        FindIterable<Document> registro = null;
        try {
            // obtener todos los registros de la colección que cumplan la condición de campo = valor
            registro = database.getCollection(clase.getSimpleName()).find(eq(campo, valor));
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer las mesas");
        }
        return registro;
    }

    /**
     * Inserta el objeto en la BBDD.
     * El objeto debe tener los mismos campos que la colección de la BBDD.
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
     * Actualiza el registro de la BBDD con el documento antiguo pasado por parámetro y el nuevo objeto.
     * El objeto debe tener los mismos campos que la colección de la BBDD.
     * El documento antiguo debe tener el campo _id con el valor del registro a actualizar.
     * @param antes documento antiguo
     * @param despues objeto nuevo
     */
    public void actualizar(Document antes, Object despues) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", antes.get("_id"));
        try {
            BasicDBObject updateQuery = new BasicDBObject();
            BasicDBObject updated = new BasicDBObject();
            for (Field f : despues.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                updated.append(f.getName(), f.get(despues));
            }
            updateQuery.append("$set", updated);
            database.getCollection(despues.getClass().getSimpleName()).updateMany(searchQuery, updateQuery);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Borra el modelo pasado de la BBDD
     * @param doc documento a borrar
     * @param clase clase del objeto a borrar
     */
    public void borrar(Document doc, Class clase) {
        database.getCollection(clase.getSimpleName()).deleteOne(doc);
    }

    /**
     * Establece la conexión con la BBDD y obtiene la base de datos.
     */
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
