package com.example.model;

import java.util.Date;

import org.bson.types.ObjectId;

/**
 * @author NathanaelGermain
 * 
 * 
 */
public class Posts {
    
    private ObjectId _id; // Unique ID for post
    private String _idString; // Unique ID in string form
    private String email; // email of the user who posted
    private String mediaLink; // Link to the photo uploaded
    private Date createdAt; // Local Date of user who posted
    private String caption; // Caption of the post
    private int likes; // Total Likes on post

    // Constructor
    public Posts(@SuppressWarnings("exports") ObjectId _id, String _idString, String email, String mediaLink, Date createdAt, String caption, int likes) {
        this._id = _id;
        this._idString = _idString;
        this.email = email;
        this.mediaLink = mediaLink;
        this.createdAt = createdAt;
        this.caption = caption;
        this.likes = likes;
    }

    //region Setters

    @SuppressWarnings("exports")
    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void set_idString(String _idString) {
        this._idString = _idString;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    //endregion

    //region Getters

    @SuppressWarnings("exports")
    public ObjectId get_id() {
        return _id;
    }

    public String get_idString() {
        return _idString;
    }

    public String getEmail() {
        return email;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCaption() {
        return caption;
    }

    public int getLikes() {
        return likes;
    }

    //endregion

    public void insertPosts() {

        // TODO: Call Function

    }

}
