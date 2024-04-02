package com.example.model;

import java.time.LocalDate;

import org.bson.Document;
import org.bson.types.ObjectId;

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

    // Default Constructor
    public Posts(String userName, String mediaLink, LocalDate dateOfPost, int likes) {
        this.userName = userName;
        this.mediaLink = mediaLink;
        this.dateOfPost = dateOfPost;
        this.likes = likes;
    }

    // Constructor with minimum parameters
    public Posts(String userName, String mediaLink, LocalDate dateOfPost) {
        this.userName = userName;
        this.mediaLink = mediaLink;
        this.dateOfPost = dateOfPost;
        this.likes = 0;
    }

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

    public void insertPosts() {

        Document doc = new Document()
                        .append("_id", new ObjectId())
                        .append("userName", userName)
                        .append("mediaLink", mediaLink)
                        .append("dateOfPost", dateOfPost)
                        .append("likes", likes);

        MongoDBConnect.insert(doc, "Posts");

    }

}
