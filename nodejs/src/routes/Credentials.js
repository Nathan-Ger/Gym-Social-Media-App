// Import express and set up the router
const express = require('express');
const router = express.Router();

// Import and initialize the MongoDB Realm SDK
const Realm = require('realm');
const app = new Realm.App({ id: 'gymsocialbefit-rzkqhmz' });

// Import the User model
const User = require('../models/User');

/**
 * Credentials.js
 * @author Nathanael Germain
 *
 * This script handles user authentication using MongoDB Realm.
 * It provides routes for user login and signup.
 */

// Logging in function, reduces code duplication
async function loginUser(email, password) {
    const credentials = Realm.Credentials.emailPassword(email, password);
    return await app.logIn(credentials);
}

// Defined Login Route, will get the email and password passed to it (as a .JSON object) and attempt to log the user in
router.post('/login', async (req, res) => {
    let { email, password } = req.body; // Gets email and password from the request body

    try {
        const user = await loginUser(email, password); // Attempts to log the user in

        // If successful, return a success message
        res.json({
            status: "SUCCESS",
            message: "Login successful"
        });
    } catch (err) {
        res.json({
            status: "FAILED",
            message: "An error occurred while logging in",
            data: err
        });
    }
});

// Defined Signup Route, will get the email, password, and username passed to it (as a .JSON object) and attempt to create a new user. If successful, it will also log the user in.
router.post('/signup', async (req, res) => {
    let { email, password, username } = req.body; // Gets email, password, and username from the request body. These are the required for creating a user and a log-in credential.

    try {
        // Attempt to register a new user, email is automatically authorized.
        await app.emailPasswordAuth.registerUser({ email, password });

        // If successful, return a success message
        res.json({
            status: "SUCCESS",
            message: "Signup successful"
        });

        try {
        const user = await loginUser(email, password); // Attempts to log the user in

        res.json({
            status: "SUCCESS",
            message: "Login successful"
        });
        } catch (err) {
            res.json({
                status: "FAILED",
                message: "An error occurred while logging in",
                data: err
            });
        }

    } catch (err) {
        res.json({
            status: "FAILED",
            message: "An error occurred while signing up",
            data: err
        });
    }
});

// Module export
module.exports = router;