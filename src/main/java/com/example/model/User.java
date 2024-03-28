package com.example.model;

import java.time.LocalDate;
import java.util.Set;

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
    private LocalDate birthDay;
    private int startingWeight; // lbs
    private int currentWeight; // lbs
    private int goalWeight; // lbs
    private int height; // inches
    private String profilePicture; // string of link
    private int totalTimeInGym; // Seconds
    private int totalCaloriesBurned; // calories
    private String[] exercises;

}
