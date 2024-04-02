package com.example.model;

import java.time.LocalDate;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * @author NathanaelGermain
 * 
 * Reviews is held as a document within Locations, as such is not stored in its own database.
 * 
 */
public class Reviews {
    
    private String userName; // Username of the person posting the review
    private double rating; // Rating the user gave
    private String comment; // Any comments the user posted
    private LocalDate dateOfComment; // Local date of user posting review

    // Default Constructor
    public Reviews(String userName, double rating, String comment, LocalDate dateOfComment) {
        this.userName = userName;
        this.rating = rating;
        this.comment = comment;
        this.dateOfComment = dateOfComment;
    }

    // Constructor with minimum parameters
    public Reviews(String userName, double rating, LocalDate dateOfComment) {
        this.userName = userName;
        this.rating = rating;
        comment = null;
        this.dateOfComment = dateOfComment;
    }

    //region Setters

    // Setter for userName
    public void setUserNameofPoster(String userName) {
        this.userName = userName;
    }
    
    // Setter for rating
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    // Setter for comment
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    // Setter for dateOfComment
    public void setDateOfComment(LocalDate dateOfComment) {
        this.dateOfComment = dateOfComment;
    }

    //endregion

    //region Getters

    // Getter for userName
    public String getUserName() {
        return userName;
    }
    
    // Getter for rating
    public double getRating() {
        return rating;
    }
    
    // Getter for comment
    public String getComment() {
        return comment;
    }
    
    // Getter for dateOfComment
    public LocalDate getDateOfComment() {
        return dateOfComment;
    }

    //endregion

    public void insertReviews() {

        Document doc = new Document()
                        .append("_id", new ObjectId())
                        .append("userName", userName)
                        .append("rating", rating)
                        .append("comment", comment)
                        .append("dateOfComment", dateOfComment);

        //MongoDBConnect.insert(doc, "Posts");
        // Will be added onto the location, does not have its own, separate database.

    }

}
