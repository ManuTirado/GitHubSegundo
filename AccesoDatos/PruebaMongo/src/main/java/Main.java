import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class Main {
    private final static ConnectionString CONN_STRING = new ConnectionString("mongodb+srv://mtirado:1234@cluster0.5th56bm.mongodb.net/?retryWrites=true&w=majority");
    private final static String DATABASE_NAME = "prueba";
    private static MongoDatabase database;

    public static void main(String[] args) {
        conexion();

        find("nombre", "Antonio");

        actualizar("Antonio","Antonio2");

        find("nombre", "Antonio");
        find("nombre", "Antonio2");
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

    private static void find(String campo, String valor) {
        MongoCollection<Document> collection = database.getCollection("Alumnos");
        Document doc = collection.find(eq(campo, valor)).first();
        if (doc != null) {
            System.out.println(doc.toJson());
        } else {
            System.out.println("No matching documents found.");
        }
    }

    private static void insertar() {
        database.getCollection("Alumnos").insertOne(new Document()
                .append("nombre", "Antonio")
                .append("apellidos", "Gorgonzola"));
    }

    private static void actualizar(String antes, String despues) {
        Document query = new Document().append("nombre", antes);
        Bson updates = Updates.combine(
                Updates.set("nombre",despues),
                Updates.currentTimestamp("lastUpdate")
        );
        UpdateOptions options = new UpdateOptions().upsert(true);
        try {
            UpdateResult result = database.getCollection("Alumnos").updateMany(query, updates, options);
            System.out.println("Modified document count: " + result.getMatchedCount());
            System.out.println("Upserted id: " + result.getUpsertedId());
        } catch (MongoException e) {
            System.err.println("Unable to update due to an error: " + e);
        }
    }
}
