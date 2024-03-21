package com.example.model;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author NathanaelGermain
 * 
 * 
 */
public class UserWorkouts {
    
    private int totalTimeInGym; // Seconds
    private int totalCaloriesBurned; // calories
    private Set<Exercise> exercises;

    public UserWorkouts() {
        this.exercises = new TreeSet<>();
        this.totalTimeInGym = 0;
        this.totalCaloriesBurned = 0;
    }

}
