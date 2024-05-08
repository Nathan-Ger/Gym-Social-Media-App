// Import express and set up the router
const express = require('express');
const router = express.Router();

// Import the MongoDB Realm SDK and utilites
const { MongoClient, ObjectId } = require('mongodb');
const { openRealm, closeRealm, findUserByEmail } = require('../utils/realmUtils.js');

// Import and initialize the MongoDB Realm SDK
const Realm = require('realm');
const app = new Realm.App({ id: 'gymsocialbefit-rzkqhmz' });

// Import the User model
const User = require('../models/User');

/**
 * User.js
 * @author Nathanael Germain
 *
 * This file contains the routes for creating, updating, and retrieving user data.
 */

router.post('/createUser', async (req, res) => {
    let { email, username } = req.body; // Gets email and password from the request body

    // Verify that the current user's email matches the email provided in the request
    if (app.currentUser && app.currentUser.profile.email !== email) {
        return res.status(403).json({
            status: "FAILED",
            message: "Unauthorized: Email does not match the authenticated user."
        });
    }

    let realm;
    try {

        realm = await openRealm(app.currentUser); // Opens realm for user

        // Create a new User object in the realm
        const newUser = realm.write(() => {
            return realm.create(User, {
                email: email,
                username: username,
                createdAt: new Date(),
            });
        });

        res.json({
            status: "SUCCESS",
            message: "User doc created successfully",
            data: newUser
        });

    } catch (err) {
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while creating a user doc",
            data: err,
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm);
    }

});

router.post('/updateUser', async (req, res) => {
    let { field, newValue } = req.body;

    field = field.toLowerCase(); // Convert the field to lowercase for consistency

    let realm;
    try {
        realm = await openRealm(app.currentUser); // Opens realm for user

        const user = realm.objects("User")[0];

        // None of the below should ever be changed.
        if (field == "createdAt" || field == "email") {
            return res.status(400).json({
                status: "FAILED",
                message: field + " cannot be updated"
            });
        } else if (field == "birthday") {
            birthday = parseDate(newValue);
            realm.write(() => {
                user[field] = birthday;
            });
        } else if (field == "totalTimeInGym" || field == "totalCaloriesBurned") {
            // TODO: Add code to take the new value and use it + the current value to make the new value.
        } else {
            realm.write(() => {
                if (user[field] != undefined) {
                    user[field] = newValue;
                }
            });
        }

        res.json({
            status: "SUCCESS",
            message: "User " + field + " updated successfully"
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

router.get('/getUser', async (req, res) => {
    const field = req.query.field;

    if (!field) {
        return res.status(400).json({
            status: "FAILED",
            message: "No field provided"
        });
    }

    const userEmail = app.currentUser.profile.email; // Get the current user's email

    let realm;
    try {
        realm = await openRealm(app.currentUser); // Open realm for user

        // Initalize the user data subscription, allows for real-time updates
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("User"), {
                name: "user-data",
                update: true,
            });
        });

        // Retrieve the user object from the realm
        const userCollection = realm.objects(User);
        const user = userCollection.filtered('email == $0', userEmail)[0];

        if (!user) {
            return res.status(404).json({
                status: "FAILED",
                message: "User not found.",
            });
        }

        // response is the requested field from the user object, returns in success message.
        const response = {};
        response[field] = user[field];

        res.json({
            status: "SUCCESS",
            message: "Current user's data retrieved successfully",
            data: response
        });

    } catch (err) {
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while retrieving the user's data",
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm);
    }


});

// Parses an date string into a Date object using the format MMDDYYYY
function parseDate(dateString) {
    if (typeof dateString !== 'string' || dateString.length !== 8 || isNaN(dateString)) {
        throw new Error('Invalid date string. Expected format is MMDDYYYY.');
    }
    const month = dateString.substring(0, 2) - 1;
    const day = dateString.substring(2, 4);
    const year = dateString.substring(4, 8);
    return new Date(year, month, day);
}

module.exports = router;