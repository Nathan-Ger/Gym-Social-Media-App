package com.example.model;

import org.bson.Document;
import org.bson.types.ObjectId;

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
    private double rating; // Average rating on location, displayed to second decimal place

    // Default Constructor
    public Locations(String locationName, String address, String zipCode, String state, double rating) {
        this.locationName = locationName;
        this.address = address;
        this.zipCode = zipCode;
        this.state = state;
        this.rating = rating;
    }

    // Constructor with minimum parameters
    public Locations(String locationName, String address, String zipCode, String state) {
        this.locationName = locationName;
        this.address = address;
        this.zipCode = zipCode;
        this.state = state;
        this.rating = 0;
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
public void insertLocations() {

        Document doc = new Document()
                        .append("_id", new ObjectId())
                        .append("locationName", locationName)
                        .append("address", address)
                        .append("zipCode", zipCode)
                        .append("state", state)
                        .append("rating", rating);
                         
        MongoDBConnect.insert(doc, "Locations");

    }


}
