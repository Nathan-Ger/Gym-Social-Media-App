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
const User = require('../models/User');

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

        realm = await openRealm(app.currentUser);

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
        // If an error occurs, return an error message with an error code
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
    const { updates } = req.body;

    let model = "User";
    let realm;
    try {
        
        realm = await openRealm(app.currentUser);

        const user = realm.objects(User);
        console.log("User:", user);
        const userId = user[0]._id;
        console.log("User ID:", userId);

        // Call a generalized update function (ensure you've defined this function or similar)
        //const id = await findUserByEmail(app, realm, app.currentUser);

        //console.log("ID: ", id);

        res.json({
            status: "SUCCESS",
            message: "User updated successfully"
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

module.exports = router;