package com.example.model;

import java.time.LocalDate;

/**
 * @author NathanaelGermain
 * 
 * 
 */
public class Exercise {
    
    private String userName; // Used to connect to User object
    private String email; // Used to connect to User Object
    private String typeofExercise; // Name of exercise
    private boolean lifted; // false if ran, true if lifted weights
    private LocalDate dateOfWorkout;
    private int timeSpent; // Seconds
    private int caloriesBurned; // calories
    private int sets; // Will show if lifted is true; Default is 1
    private double milesRan; // miles, will only show if lifted is false; Default is 0
    private int weightLifted; // lbs, will only show if lifted is true; Default is 0

    //region Setters

    // Setter for userName
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter for type of exercise
    public void setTypeOfExercise(String typeofExercise) {
        this.typeofExercise = typeofExercise;
    }

    // Setter for lifted status
    public void setLifted(boolean lifted) {
        this.lifted = lifted;
    }

    // Setter for date of workout
    public void setDateOfWorkout(LocalDate dateOfWorkout) {
        this.dateOfWorkout = dateOfWorkout;
    }

    // Setter for time spent
    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    // Setter for calories burned
    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    // Setter for sets
    public void setSets(int sets) {
        this.sets = sets;
    }

    // Setter for miles ran
    public void setMilesRan(double milesRan) {
        this.milesRan = milesRan;
    }

    // Setter for weight lifted
    public void setWeightLifted(int weightLifted) {
        this.weightLifted = weightLifted;
    }


    //endregion

    //region Getters

    // Getter for userName
    public String getUserName() {
        return userName;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for type of exercise
    public String getTypeOfExercise() {
        return typeofExercise;
    }

    // Getter for lifted status
    public boolean isLifted() {
        return lifted;
    }

    // Getter for date of workout
    public LocalDate getDateOfWorkout() {
        return dateOfWorkout;
    }

    // Getter for time spent
    public int getTimeSpent() {
        return timeSpent;
    }

    // Getter for calories burned
    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    // Getter for sets
    public int getSets() {
        return sets;
    }

    // Getter for miles ran
    public double getMilesRan() {
        return milesRan;
    }

    // Getter for weight lifted
    public int getWeightLifted() {
        return weightLifted;
    }

    //endregion
    

}
