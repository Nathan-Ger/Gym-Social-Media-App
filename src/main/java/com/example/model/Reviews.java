package com.example.model;
import java.util.Date;

import org.bson.types.ObjectId;

/** Reviews.java
 * @author Nathanael Germain
 *
 * Lines up with MongoDB Schema
 */
public class Reviews {
    
    private ObjectId _id; // Unique ID for review
    private String _idString; // Unique ID in string form
    private String email; // Username of the person posting the review
    private double rating; // Rating the user gave
    private String review; // Any comments the user posted
    private Date createdAt; // Local date of user posting review
    private String location_id; // ID of the location the review is for

    // Constructor
    public Reviews(@SuppressWarnings("exports") ObjectId _id, String _idString, String email, double rating, String review, Date createdAt, String location_id) {
        this._id = _id;
        this._idString = _idString;
        this.email = email;
        this.rating = rating;
        this.review = review;
        this.createdAt = createdAt;
        this.location_id = location_id;
    }

    //region Setters

    // Setter for _id
    @SuppressWarnings("exports")
    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    // Setter for _idString
    public void set_idString(String _idString) {
        this._idString = _idString;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter for rating
    public void setRating(double rating) {
        this.rating = rating;
    }

    // Setter for review
    public void setReview(String review) {
        this.review = review;
    }

    // Setter for createdAt
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Setter for location_id
    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    //endregion

    //region Getters

    // Getter for _id
    @SuppressWarnings("exports")
    public ObjectId get_id() {
        return _id;
    }

    // Getter for _idString
    public String get_idString() {
        return _idString;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for rating
    public double getRating() {
        return rating;
    }

    // Getter for review
    public String getReview() {
        return review;
    }

    // Getter for createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    // Getter for location_id
    public String getLocation_id() {
        return location_id;
    }

    //endregion
    

    public void insertReviews() {

        // Call function

    }

}
