package com.example.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.MongoUpdatedEncryptedFieldsException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.*;

/**
 * @author NathanaelGermain
 * 
 * 
 */
public class MongoDBConnect {

    private static MongoDatabase database;
    private static MongoClient mongoClient;

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

}