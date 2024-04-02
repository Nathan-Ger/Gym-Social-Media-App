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
    private static MongoCollection<Document> collection;
    private static MongoClient mongoClient;
    private static MongoClientSettings settings;

    public static void mongoDB() {

        if (mongoClient != null) {
            return;
        }

        String dbPassword = System.getenv("MONGO_DB_PASSWORD");
        if (dbPassword == null || dbPassword.isEmpty())
            throw new IllegalStateException("MongoDB password not set in the environment variables.");

        String connectionString = "mongodb+srv://nathanlgermain:" + dbPassword + "@befit.uxd3dmt.mongodb.net/?retryWrites=true&w=majority&appName=BeFit";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

    
                /*
        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                database = mongoClient.getDatabase("Befit");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }*/

        
        
    }

    public static void collectionChange(String collectionName) {

        if (database == null) {
            System.err.println("Database connection is not initialized");
            return;
        }

        MongoCollection<Document> collection = database.getCollection(collectionName);

    }

    public static void insertUser(User u) {

        //mongoDB();
        
        /*if (database == null || mongoClient == null) {
            System.err.println("Database connection is not initialized");
            return;
        }*/

        String dbPassword = System.getenv("MONGO_DB_PASSWORD");
        if (dbPassword == null || dbPassword.isEmpty())
            throw new IllegalStateException("MongoDB password not set in the environment variables.");

        String connectionString = "mongodb+srv://nathanlgermain:" + dbPassword + "@befit.uxd3dmt.mongodb.net/?retryWrites=true&w=majority&appName=BeFit";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                database = mongoClient.getDatabase("Befit");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");

                Document doc = new Document()
                        .append("_id", new ObjectId())
                        .append("firstName", u.getFName())
                        .append("lastName", u.getLName())
                        .append("email", u.getEmail())
                        .append("phoneNumber", u.getPhoneNumber())
                        .append("birthday", u.getBirthday())
                        .append("startingWeight", u.getStartingWeight())
                        .append("currentWeight", u.getCurrentWeight())
                        .append("goalWeight", u.getGoalWeight())
                        .append("height", u.getHeight())
                        .append("profilePicture", u.getProfilePicture())
                        .append("totalTimeInGym", u.getTotalTimeInGym())
                        .append("totalCaloriesBurned", u.getTotalCaloriesBurned());

                        database.getCollection("Users").insertOne(doc);

            } catch (MongoException e) {
                e.printStackTrace();
            }
        }

    }

    public static void insertExercise(Exercise e) {



    }


}