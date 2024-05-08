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
const Reviews = require('../models/Reviews');

router.post('/createReview', async (req, res) => {
    let { locationId, rating, review } = req.body;

    // Below field are not required.
    review = review || "";
    createdAt = new Date();

    let realm;
    try {

        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Reviews"), {
                name: "all-reviews",
                update: true,
            });
        });

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
        // If an error occurs, return an error message with an error code
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while creating an Reviews doc",
            data: err,
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm);
    }

});

router.post('/updateReview', async (req, res) => {
    let { reviewId, field, newValue } = req.body;

    let realm;
    try {

        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Reviews"), {
                name: "all-reviews",
                update: true,
            });
        });

        const review = realm.objects("Reviews").filtered("_idString == $0", reviewId)[0];

        if (!review) {
            return res.status(400).json({
                status: "FAILED",
                message: "review not found"
            });
        }
        
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
        realm = await closeRealm(realm);
    }

});

router.get('/getUserReviews', async (req, res) => {

    const userEmail = app.currentUser.profile.email;

    let realm;
    try {
        // Open the realm
        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Reviews"), {
                name: "reviews-data",
                update: true,
            });
        });

        const reviewsCollection = realm.objects(Reviews);
        const reviews = reviewsCollection.filtered('email == $0', userEmail);
        const reviewsArray = Array.from(reviews);

        res.json({
            status: "SUCCESS",
            message: "Current user's review data retrieved successfully",
            data: reviewsArray
        });

    } catch (err) {
        // Handle any errors that occur during retrieval
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while retrieving the user's review data",
            error: err.message
        });
    } finally {
        if (realm)
            realm = await closeRealm(realm);
    }


});

router.get('/getReviewsByLocation', async (req, res) => {
    const location_id = req.query.location_id;

    const userEmail = app.currentUser.profile.email;

    let realm;
    try {
        // Open the realm
        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Reviews"), {
                name: "reviews-data",
                update: true,
            });
        });

        console.log("location_id", location_id);

        const reviewsCollection = realm.objects(Reviews);
        console.log("reviewsCollection", reviewsCollection);
        const reviews = reviewsCollection.filtered('location_id == $0', location_id);
        const reviewsArray = Array.from(reviews);

        res.json({
            status: "SUCCESS",
            message: "Current user's review data retrieved successfully",
            data: reviews
        });

    } catch (err) {
        // Handle any errors that occur during retrieval
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while retrieving the user's review data",
            error: err.message
        });
    } finally {
        if (realm)
            realm = await closeRealm(realm);
    }


});

module.exports = router;