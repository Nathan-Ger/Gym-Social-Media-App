package com.example.model;
import java.util.Date;

import org.bson.types.ObjectId;

/** User.java
 * @author Nathanael Germain
 *
 * Lines up with MongoDB Schema
 */
public class User {
    
    private ObjectId _id; // Unique ID for user
    private String fName; // First Name
    private String lName; // Last Name
    private String username; // Username
    private String email; // Email
    private String phoneNumber; // Phone Number
    private Date birthday; // Date of Birth
    private double startingWeight; // lbs
    private double currentWeight; // lbs
    private double goalWeight; // lbs
    private double height; // inches
    private String profilePicture; // string of link
    private double totalTimeInGym; // Seconds
    private double totalCaloriesBurned; // calories

    // Constructor
    public User(@SuppressWarnings("exports") ObjectId _id, String fName, String lName,
                    String username, String email, String phoneNumber, Date birthday,
                    double startingWeight, double currentWeight, double goalWeight,
                    double height, String profilePicture, double totalTimeInGym,
                    double totalCaloriesBurned) {
        this._id = _id;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
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

    //region Setters

    // Setter for _id
    @SuppressWarnings("exports")
    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    // Setter for fName
    public void setfName(String fName) {
        this.fName = fName;
    }

    // Setter for lName
    public void setlName(String lName) {
        this.lName = lName;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter for phoneNumber
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Setter for birthday
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    // Setter for startingWeight
    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }

    // Setter for currentWeight
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    // Setter for goalWeight
    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }

    // Setter for height
    public void setHeight(double height) {
        this.height = height;
    }

    // Setter for profilePicture
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    // Setter for totalTimeInGym
    public void setTotalTimeInGym(double totalTimeInGym) {
        this.totalTimeInGym = totalTimeInGym;
    }

    // Setter for totalCaloriesBurned
    public void setTotalCaloriesBurned(double totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    //endregion

    //region Getters

    // Getter for _id
    @SuppressWarnings("exports")
    public ObjectId get_id() {
        return _id;
    }

    // Getter for fName
    public String getfName() {
        return fName;
    }

    // Getter for lName
    public String getlName() {
        return lName;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Getter for birthday
    public Date getBirthday() {
        return birthday;
    }

    // Getter for startingWeight
    public double getStartingWeight() {
        return startingWeight;
    }

    // Getter for currentWeight
    public double getCurrentWeight() {
        return currentWeight;
    }

    // Getter for goalWeight
    public double getGoalWeight() {
        return goalWeight;
    }

    // Getter for height
    public double getHeight() {
        return height;
    }

    // Getter for profilePicture
    public String getProfilePicture() {
        return profilePicture;
    }

    // Getter for totalTimeInGym
    public double getTotalTimeInGym() {
        return totalTimeInGym;
    }

    // Getter for totalCaloriesBurned
    public double getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    //endregion

    public void insertUser() {

        // TODO: Call Function

    }

    public static void updateUserName(String searchableUserName, String newUserName) {

        // TODO: Call Function

    }

}
