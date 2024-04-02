package com.example.model;


/**
 * @author NathanaelGermain
 * 
 * 
 */
public class Locations {
    
    private String locationName; // Name of the location
    private String address; // Street Address
    private String zipCode; // Zip Code
    private String state; // State ex. NY, CA, TX
    private double rating; // Average rating on location

    //region Setters

    // Setter for locationName
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }

    // Setter for zipCode
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    // Setter for state
    public void setState(String state) {
        this.state = state;
    }

    // Setter for rating
    public void setRating(double rating) {
        this.rating = rating;
    }

    //endregion

    //region Getters

    // Getter for locationName
    public String getLocationName() {
        return locationName;
    }
    
    // Getter for address
    public String getAddress() {
        return address;
    }
    
    // Getter for zipCode
    public String getZipCode() {
        return zipCode;
    }
    
    // Getter for state
    public String getState() {
        return state;
    }
    
    // Getter for rating
    public double getRating() {
        return rating;
    }

    //endregion

}
