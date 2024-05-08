package com.example.model;

import org.bson.types.ObjectId;

/**
 * @author NathanaelGermain
 * 
 * 
 */
public class Locations {

    private ObjectId _id; // Unique ID for location
    private String _idString;
    private String locationName; // Name of the location
    private String address; // Street Address
    private String zipCode; // Zip Code
    private String state; // State ex. NY, CA, TX
    private double averageRating; // Average rating on location, displayed to second decimal place

    // Default Constructor
    public Locations(@SuppressWarnings("exports") ObjectId _id, String _idString, String locationName, String address, String zipCode, String state, double averageRating) {
        this._id = _id;
        this._idString = _idString;
        this.locationName = locationName;
        this.address = address;
        this.zipCode = zipCode;
        this.state = state;
        this.averageRating = averageRating;
    }

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

    // Setter for averageRating
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    //endregion

    //region Getters

    // Getter for _id, setter not needed
    @SuppressWarnings("exports")
    public ObjectId get_id() {
        return _id;
    }

    // Getter for _idString, setter not needed
    public String get_idString() {
        return _idString;
    }

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
    public double getAverageRating() {
        return averageRating;
    }

    //endregion
public void insertLocations() {

        // TODO: Call function

    }


}
