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

    private static void initializeMongoDB() {

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

    private static void closeMongoDB() {

        if (mongoClient == null) {
            System.out.println("\n\n\nNo MongoDB connection active.");
            return;
        }

        mongoClient.close();
        mongoClient = null;
        System.out.println("\n\n\nMongoDB connection closed");

    }

    public static void insert(@SuppressWarnings("exports") Document doc, String collectionName) {

        initializeMongoDB();

        database.getCollection(collectionName).insertOne(doc);

        closeMongoDB();

    }

}