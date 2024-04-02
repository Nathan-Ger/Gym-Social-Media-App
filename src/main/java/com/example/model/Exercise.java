package com.example.model;

import java.time.LocalDate;

/**
 * @author NathanaelGermain
 * 
 * 
 */
public class Exercise {
    

    //test
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

}
