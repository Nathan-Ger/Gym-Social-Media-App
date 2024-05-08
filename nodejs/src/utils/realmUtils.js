const Realm = require('realm');
const User = require('../models/User');
const Exercise = require('../models/Exercise');
const Posts = require('../models/Posts');
const Locations = require('../models/Locations');
const Reviews = require('../models/Reviews');

// Function to open a Realm with a specified user and configuration
async function openRealm(user) {

    const config = {
        schema: [User, Exercise, Posts, Locations, Reviews], // Ensure the schema is correctly referenced
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

// Function to close a Realm
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
async function findUserByEmail(app, realm, user) {

    console.log("User:", user)
    console.log("Current user:", app.currentUser);
    console.log("User data:", app.currentUser.data);
    console.log("User email:", user.data.email)


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
