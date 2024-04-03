package com.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.MongoUpdatedEncryptedFieldsException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

/**
 * @author NathanaelGermain
 * 
 * 
 */
public class MongoDBConnect {

    private static MongoDatabase database;
    private static MongoClient mongoClient;

    //region Initalize and Close Methods

    /** void initalizeMongoDB()
     * @author NathanaelGermain
     * 
     * Starts a MongoDB client to access the database.
     * Static so it can be accessed without an object.
     * Private so only methods within this file can access.
     */
    private static void initializeMongoDB() {

        // Will not intitalize the database if a database is already initialized
        if (mongoClient != null) {
            System.out.println("\n\n\nCurrent MongoDB connection active.");
            return; // Current mongoClient running
        }

        String dbPassword = System.getenv("MONGO_DB_PASSWORD");
        if (dbPassword == null || dbPassword.isBlank())
            throw new IllegalStateException("\n\n\nMongoDB password not set in the environment variable");

        String connectionString = "mongodb+srv://nathanlgermain:" + dbPassword + 
                                "@befit.uxd3dmt.mongodb.net/?retryWrites=true&w=majority&appName=BeFit";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("Befit");
        System.out.println("\n\n\nMongoDB initialized and ready for use.");
        

    }

    /** void closeMongoDB()
     * @author NathanaelGermain
     * 
     * Closes the MongoDB client.
     * Static so it can be accessed without an object.
     * Private so only methods within this file can access.
     */
    private static void closeMongoDB() {

        // Will not close the client if no client is running
        if (mongoClient == null) { 
            System.out.println("\n\n\nNo MongoDB connection active.");
            return;
        }

        mongoClient.close();
        mongoClient = null; // Sets mongoClient to null so we can make sure it is open/closed
        System.out.println("\n\n\nMongoDB connection closed");

    }

    //endregion

    //region Methods

    public static boolean findDuplicateUserNames(String userName) {

        MongoCollection<Document> collection = database.getCollection("Users");
        Document document = collection.find(Filters.eq("userName", userName)).first();

        if (document != null)
            return true;

        return false;

    }

    public static List<String> findIdWithUserName(String userName, String collectionName) {

        List<String> ids = new ArrayList<>();
        initializeMongoDB();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        
        //Document document = collection.find(Filters.eq("userName", userName)).first();
        FindIterable<Document> documents = collection.find(Filters.eq("userName", userName));

        for (Document document : documents) {
            Object id = document.getObjectId("_id");
            ids.add(id.toString());
        }

        closeMongoDB();

        return ids;

    }

    /** void insert(Document, String)
     * @author NathanaelGermain
     * 
     * Inserts a document into a collection.
     * Kept generalized to help reduce wasteful methods.
     * Static so can be accessed without an object.
     * 
     * @param doc This is the document that will be inserted.
     * @param collectionName This is the name of the collection the insertion will happen within.
     */
    public static void insert(@SuppressWarnings("exports") Document doc, String collectionName) {

        initializeMongoDB();
        database.getCollection(collectionName).insertOne(doc); // Inserts the Document, doc, into the collection, collectionName 
        closeMongoDB();

    }

    public static <T> void update(List<String> ids, String collectionName, String key, T value) {


        initializeMongoDB();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        List<ObjectId> objectIds = ids.stream().map(ObjectId::new).collect(Collectors.toList()); // Convert String Ids to ObjectId

        Bson filter = Filters.in("_id", objectIds);
        UpdateResult result = collection.updateMany(filter, Updates.set(key, value));

        System.out.println("\n\n\nUpdated " + result.getModifiedCount() + " documents.");

        closeMongoDB();

    }

    //endregion

}