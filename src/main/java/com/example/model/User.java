package com.example.model;

import java.time.LocalDate;

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
    //private String[] exercises;

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

    /*Getter for exercises
    public String[] getExercises() {
        return exercises.clone(); // Return a clone to ensure encapsulation
    } */

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

    /*Setter for exercises
    public void setExercises(String[] exercises) {
        this.exercises = exercises.clone(); // Clone the array for encapsulation
    } */

    //endregion

}
