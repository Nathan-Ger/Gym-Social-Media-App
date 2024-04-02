package com.example.model;

import java.time.LocalDate;

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

}
