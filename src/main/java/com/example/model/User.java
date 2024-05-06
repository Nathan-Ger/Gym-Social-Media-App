package com.example.model;

import java.time.LocalDate;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * @author NathanaelGermain
 * 
 * 
 */
public class User {
    
    private String fName;
    private String lName;
    private String userName;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private int startingWeight; // lbs
    private int currentWeight; // lbs
    private int goalWeight; // lbs
    private int height; // inches
    private String profilePicture; // string of link
    private int totalTimeInGym; // Seconds
    private int totalCaloriesBurned; // calories

    //region Constructors

    // Default Constructor
    public User(String fName, String lName, String userName, String email,
                String phoneNumber, LocalDate birthday, int startingWeight,
                int currentWeight, int goalWeight, int height,
                String profilePicture, int totalTimeInGym, int totalCaloriesBurned) {
        this.fName = fName;
        this.lName = lName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.startingWeight = startingWeight;
        this.currentWeight = currentWeight;
        this.goalWeight = goalWeight;
        this.height = height;
        this.profilePicture = profilePicture;
        this.totalTimeInGym = totalTimeInGym;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    // Constructor with only the necessary items.
    public User(String fName, String lName, String userName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = null;
        this.birthday = null;
        this.startingWeight = 0;
        this.goalWeight = 0;
        this.height = 0;
        this.profilePicture = null;
        this.totalTimeInGym = 0;
        this.totalCaloriesBurned = 0;
    }

    //endregion

    //region Setters

    // Setter for fName
    public void setFName(String fName) {
        this.fName = fName;
    }

    // Setter for lName
    public void setLName(String lName) {
        this.lName = lName;
    }

    // Setter for userName
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter for phoneNumber
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Setter for birthDay
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    // Setter for startingWeight
    public void setStartingWeight(int startingWeight) {
        this.startingWeight = startingWeight;
    }

    // Setter for currentWeight
    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    // Setter for goalWeight
    public void setGoalWeight(int goalWeight) {
        this.goalWeight = goalWeight;
    }

    // Setter for height
    public void setHeight(int height) {
        this.height = height;
    }

    // Setter for profilePicture
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    // Setter for totalTimeInGym
    public void setTotalTimeInGym(int totalTimeInGym) {
        this.totalTimeInGym = totalTimeInGym;
    }

    // Setter for totalCaloriesBurned
    public void setTotalCaloriesBurned(int totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    //endregion

    //region Getters

    // Getter for fName
    public String getFName() {
        return fName;
    }

    // Getter for lName
    public String getLName() {
        return lName;
    }

    // Getter for userName
    public String getUserName() {
        return userName;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Getter for birthDay
    public LocalDate getBirthday() {
        return birthday;
    }

    // Getter for startingWeight
    public int getStartingWeight() {
        return startingWeight;
    }

    // Getter for currentWeight
    public int getCurrentWeight() {
        return currentWeight;
    }

    // Getter for goalWeight
    public int getGoalWeight() {
        return goalWeight;
    }

    // Getter for height
    public int getHeight() {
        return height;
    }

    // Getter for profilePicture
    public String getProfilePicture() {
        return profilePicture;
    }

    // Getter for totalTimeInGym
    public int getTotalTimeInGym() {
        return totalTimeInGym;
    }

    // Getter for totalCaloriesBurned
    public int getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    //endregion

    //region Methods

    public void insertUser() {

        // Confirms there is not already the same username in the database
        if (MongoDBConnect.findDuplicateUserNames(userName)) {
            System.out.println("\n\n\nDuplicate username found.");
            return;
        }

        // Creates a Document with the given user
        Document doc = new Document()
                        .append("_id", new ObjectId())
                        .append("fName", fName)
                        .append("lName", lName)
                        .append("userName", userName)
                        .append("email", email)
                        .append("phoneNumber", phoneNumber)
                        .append("birthday", birthday)
                        .append("startingWeight", startingWeight)
                        .append("currentWeight", currentWeight)
                        .append("goalWeight", goalWeight)
                        .append("height", height)
                        .append("profilePicture", profilePicture)
                        .append("totalTimeInGym", totalTimeInGym)
                        .append("totalCaloriesBurned", totalCaloriesBurned);

        MongoDBConnect.insert(doc, "Users"); // Inserts user into database

    }

    public static void updateUserName(String searchableUserName, String newUserName) {

        // Confirms there is not already the same username in the database
        if (MongoDBConnect.findDuplicateUserNames(newUserName)) {
            System.out.println("\n\n\nDuplicate username found.");
            return;
        }

        // Updates Users by username
        List<String> userIds = MongoDBConnect.findIdWithUserName(searchableUserName, "Users");
        MongoDBConnect.update(userIds, "Users", "userName", newUserName);

        // Updates Excerises by username
        List<String> exerciseIds = MongoDBConnect.findIdWithUserName(searchableUserName, "Exercise");
        MongoDBConnect.update(exerciseIds, "Exercise", "userName", newUserName);

        // Updates Posts by username
        List<String> postIds = MongoDBConnect.findIdWithUserName(searchableUserName, "Posts");
        MongoDBConnect.update(postIds, "Posts", "userName", newUserName);

    }

    //endregion

}
