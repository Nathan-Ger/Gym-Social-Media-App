// Import express and set up the router
const express = require('express');
const router = express.Router();

// Import the MongoDB Realm SDK and the utilites functions
const { MongoClient, ObjectId } = require('mongodb');
const { openRealm, closeRealm, findUserByEmail } = require('../utils/realmUtils.js');

// Import and initialize the MongoDB Realm SDK
const Realm = require('realm');
const app = new Realm.App({ id: 'gymsocialbefit-rzkqhmz' });

// Import the User model
const Reviews = require('../models/Reviews');

/**
 * Reviews.js
 * @author Nathanael Germain
 *
 * This file contains the routes for creating, updating, and retrieving review data.
 */

router.post('/createReview', async (req, res) => {
    let { locationId, rating, review } = req.body;

    // Below field are not required.
    review = review || "";
    createdAt = new Date();

    let realm;
    try {
        realm = await openRealm(app.currentUser); // Opens realm for user

        // Open a subscription to the Reviews collection, required to access the data in Realm.
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Reviews"), {
                name: "all-reviews",
                update: true,
            });
        });

        // Create a new Reviews object in the realm
        let newReview;
        realm.write(() => {
            const newId = new Realm.BSON.ObjectId();
            newReview = realm.create(Reviews, {
                _id: newId,
                _idString: newId.toString(),
                email: app.currentUser.profile.email,
                rating: rating,
                review: review,
                createdAt: createdAt,
                location_id: locationId
            });
        });

        res.json({
            status: "SUCCESS",
            message: "Reviews doc created successfully",
            data: newReview
        });

    } catch (err) {
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while creating an Reviews doc",
            data: err,
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm); // Close the realm
    }

});

router.post('/updateReview', async (req, res) => {
    let { reviewId, field, newValue } = req.body;

    let realm;
    try {
        realm = await openRealm(app.currentUser); // Opens realm for user

        // Open a subscription to the Reviews collection, required to access the data in Realm.
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Reviews"), {
                name: "all-reviews",
                update: true,
            });
        });

        // Retrieve the review object from the realm using the reviews _id
        const review = realm.objects("Reviews").filtered("_idString == $0", reviewId)[0];

        // Check if the review exists
        if (!review) {
            return res.status(400).json({
                status: "FAILED",
                message: "review not found"
            });
        }
        
        // Update the review object in the realm
        realm.write(() => {
            review[field] = newValue;
        });
        
        res.json({
            status: "SUCCESS",
            message: "Reviews " + field + " updated successfully"
        });
    } catch (error) {
        res.status(500).json({
            status: "FAILED",
            message: "Error updating Realm object",
            error: error.message
        });
    } finally {
        realm = await closeRealm(realm); // Close the realm
    }

});

router.get('/getUserReviews', async (req, res) => {

    const userEmail = app.currentUser.profile.email; // Get the current user's email

    let realm;
    try {
        realm = await openRealm(app.currentUser); // Opens realm for user

        // Open a subscription to the Reviews collection, required to access the data in Realm.
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Reviews"), {
                name: "reviews-data",
                update: true,
            });
        });

        // Get the reviews object from the realm, puts in an array to return.
        const reviewsCollection = realm.objects(Reviews);
        const reviews = reviewsCollection.filtered('email == $0', userEmail);
        const reviewsArray = Array.from(reviews);

        res.json({
            status: "SUCCESS",
            message: "Current user's review data retrieved successfully",
            data: reviewsArray
        });

    } catch (err) {
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while retrieving the user's review data",
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm); // Close the realm
    }


});

router.get('/getReviewsByLocation', async (req, res) => {
    const location_id = req.query.location_id;

    let realm;
    try {
        realm = await openRealm(app.currentUser); // Opens realm for user

        // Open a subscription to the Reviews collection, required to access the data in Realm.
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Reviews"), {
                name: "reviews-data",
                update: true,
            });
        });

        // Get the reviews object from the realm, puts in an array to return.
        const reviewsCollection = realm.objects(Reviews);
        const reviews = reviewsCollection.filtered('location_id == $0', location_id);
        const reviewsArray = Array.from(reviews);

        res.json({
            status: "SUCCESS",
            message: "Current user's review data retrieved successfully",
            data: reviews
        });

    } catch (err) {
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while retrieving the user's review data",
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm); // Close the realm
    }


});

module.exports = router;