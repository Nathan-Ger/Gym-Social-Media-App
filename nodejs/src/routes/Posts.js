/**
 * User.js
 * @author Nathanael Germain
 *
 *
 */

// Import express and set up the router
const express = require('express');
const router = express.Router();

const { MongoClient, ObjectId } = require('mongodb');

const { openRealm, closeRealm, findUserByEmail } = require('../utils/realmUtils.js');

require('dotenv').config(); // Not used in this script, but can be used to load environment variables from a .env file.

const bcrypt = require('bcrypt'); // Not used in this script, but can be used to hash passwords before storing them in the database. Realm does this automatically.

// Import and initialize the MongoDB Realm SDK
const Realm = require('realm');
const app = new Realm.App({ id: 'gymsocialbefit-rzkqhmz' });

// Import the User model
const Posts = require('../models/Posts');

router.post('/createPost', async (req, res) => {
    let { mediaLink, caption, createdAt, likes } = req.body;

    // Below field are not required.
    caption = caption || "";
    createdAt = createdAt || new Date();
    likes = likes || 0;

    // MediaLink is required, but for testing has a default
    mediaLink = mediaLink || "https://placeholder.com/placeholder.jpg";

    let realm;
    try {

        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Posts"), {
                name: "all-posts",
                update: true,
            });
        });

        let newPost;
        realm.write(() => {
            const newId = new Realm.BSON.ObjectId();
            newPost = realm.create(Posts, {
                _id: newId,
                _idString: newId.toString(),
                email: app.currentUser.profile.email,
                mediaLink: mediaLink,
                caption: caption,
                createdAt: createdAt,
                likes: likes
            });
        });

        res.json({
            status: "SUCCESS",
            message: "Posts doc created successfully",
            data: newPost
        });

    } catch (err) {
        // If an error occurs, return an error message with an error code
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while creating an Posts doc",
            data: err,
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm);
    }

});

router.post('/updatePost', async (req, res) => {
    let { postId, field, newValue } = req.body;

    field = field.toLowerCase();

    let realm;
    try {

        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Posts"), {
                name: "all-posts",
                update: true,
            });
        });

        const post = realm.objects("Posts").filtered("_idString == $0", postId)[0];

        if (!post) {
            return res.status(400).json({
                status: "FAILED",
                message: "Post not found"
            });
        }

        // None of the below should ever be changed.
        if (field == "email" || field == "mediaLink" || field == "createdAt") {
            return res.status(400).json({
                status: "FAILED",
                message: field + " cannot be updated"
            });
        } else {
            realm.write(() => {
                post[field] = newValue;
            });
        }

        res.json({
            status: "SUCCESS",
            message: "Posts " + field + " updated successfully"
        });
    } catch (error) {
        res.status(500).json({
            status: "FAILED",
            message: "Error updating Realm object",
            error: error.message
        });
    } finally {
        realm = await closeRealm(realm);
    }

});

router.get('/getUserPosts', async (req, res) => {

    const userEmail = app.currentUser.profile.email;

    let realm;
    try {
        // Open the realm
        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Posts"), {
                name: "posts-data",
                update: true,
            });
        });

        const postsCollection = realm.objects(Posts);
        const posts = postsCollection.filtered('email == $0', userEmail);
        const postsArray = Array.from(posts);

        res.json({
            status: "SUCCESS",
            message: "Current user's post data retrieved successfully",
            data: postsArray
        });

    } catch (err) {
        // Handle any errors that occur during retrieval
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while retrieving the user's post data",
            error: err.message
        });
    } finally {
        if (realm)
            realm = await closeRealm(realm);
    }


});

module.exports = router;