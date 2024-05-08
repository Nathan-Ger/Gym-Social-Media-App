const Realm = require('realm');
const User = require('../models/User'); // Import the User model
const Exercise = require('../models/Exercise'); // Import the Exercise model
const Posts = require('../models/Posts'); // Import the Posts model
const Locations = require('../models/Locations'); // Import the Locations model
const Reviews = require('../models/Reviews'); // Import the Reviews model

/** realmUtils.js
 * @author Nathanael Germain
 *
 * This script contains utility functions for opening and closing a Realm, as well as finding a user by email.
 * 
 * 
 */

// Function to open a Realm with a specified user and configuration
async function openRealm(user) {

    const config = {
        schema: [User, Exercise, Posts, Locations, Reviews], // Schemas realm is being opened for.
        sync: {
            user: user,
            flexible: true
        },
    };

    try {
        const realm = await Realm.open(config);
        console.log("Realm is opened for user:", user.id);
        return realm;
    } catch (error) {
        console.error("Error opening Realm:", error);
        throw error; // Rethrow to handle error outside this function
    }
}

// Function to close a Realm instance.
function closeRealm(realm) {
    if (!realm) {
        console.log("No Realm instance provided to close.");
        return;
    }
    
    if (!realm.isClosed) {
        realm.close();
        console.log("Realm has been closed successfully.");
    }
}

// Function to find a user _id by email
async function findUserByEmail(app, realm) {
    let email = app.currentUser.data.email;
    
    try {
        const user = realm.objects("User").filtered(`email = "${email}"`)[0];
        return user?._id;
    } catch (error) {
        console.error("Error finding user by email:", error);
        throw error;
    }
}

module.exports = { openRealm, closeRealm, findUserByEmail };
