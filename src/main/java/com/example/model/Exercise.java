package com.example.model;
import java.util.Date;

import org.bson.types.ObjectId;

/** Exercise.java
 * @author Nathanael Germain
 *
 * Lines up with MongoDB Schema
 */
public class Exercise {
    
    private ObjectId _id; // Unique ID for exercise
    private String _idString; // Unique ID in string form
    private String email; // Used to connect to User object
    private String typeofExercise; // Name of exercise
    private boolean lifted; // false if ran, true if lifted weights
    private Date dateOfWorkout;
    private double timeSpent; // Seconds
    private double caloriesBurned; // calories
    private int sets; // Will show if lifted is true; Default is 1
    private double miles; // miles, will only show if lifted is false; Default is 0
    private double weightLifted; // lbs, will only show if lifted is true; Default is 0

    // Constructor
    public Exercise(@SuppressWarnings("exports") ObjectId _id, String _idString, String email, String typeofExercise, boolean lifted, Date dateOfWorkout, double timeSpent, double caloriesBurned, int sets, double miles, double weightLifted) {
        this._id = _id;
        this._idString = _idString;
        this.email = email;
        this.typeofExercise = typeofExercise;
        this.lifted = lifted;
        this.dateOfWorkout = dateOfWorkout;
        this.timeSpent = timeSpent;
        this.caloriesBurned = caloriesBurned;
        this.sets = sets;
        this.miles = miles;
        this.weightLifted = weightLifted;
    }

    //region Setters

    @SuppressWarnings("exports")
    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void set_idString(String _idString) {
        this._idString = _idString;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTypeofExercise(String typeofExercise) {
        this.typeofExercise = typeofExercise;
    }

    public void setLifted(boolean lifted) {
        this.lifted = lifted;
    }

    public void setDateOfWorkout(Date dateOfWorkout) {
        this.dateOfWorkout = dateOfWorkout;
    }

    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public void setWeightLifted(double weightLifted) {
        this.weightLifted = weightLifted;
    }

    //#endregion
    
    //region Getters

    @SuppressWarnings("exports")
    public ObjectId get_id() {
        return _id;
    }

    public String get_idString() {
        return _idString;
    }

    public String getEmail() {
        return email;
    }

    public String getTypeofExercise() {
        return typeofExercise;
    }

    public boolean isLifted() {
        return lifted;
    }

    public Date getDateOfWorkout() {
        return dateOfWorkout;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public int getSets() {
        return sets;
    }

    public double getMiles() {
        return miles;
    }

    public double getWeightLifted() {
        return weightLifted;
    }

    //endregion

    
    public void insertExercise() {

        // TODO: Call function

    }

}
