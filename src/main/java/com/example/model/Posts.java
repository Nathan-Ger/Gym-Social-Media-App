package com.example.model;

import java.time.LocalDate;

/**
 * @author NathanaelGermain
 * 
 * 
 */
public class Posts {
    
    private String userName; // Username of the user who posted
    private String mediaLink; // Link to the photo uploaded
    private LocalDate dateOfPost; // Local Date of user who posted
    private int likes; // Total Likes on post

    //region Setters

    // Setter for userName
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // Setter for mediaLink
    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }
    
    // Setter for dateOfPost
    public void setDateOfPost(LocalDate dateOfPost) {
        this.dateOfPost = dateOfPost;
    }
    
    // Setter for likes
    public void setLikes(int likes) {
        this.likes = likes;
    }

    //endregion

    //region Getters

    // Getter for userName
    public String getUserName() {
        return userName;
    }
    
    // Getter for mediaLink
    public String getMediaLink() {
        return mediaLink;
    }
    
    // Getter for dateOfPost
    public LocalDate getDateOfPost() {
        return dateOfPost;
    }
    
    // Getter for likes
    public int getLikes() {
        return likes;
    }

    //endregion

}
