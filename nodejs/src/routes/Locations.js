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
const Locations = require('../models/Locations');

router.post('/createLocation', async (req, res) => {
    let { locationName, address, city, state, zipCode} = req.body;

    // Below field are not required.
    let averageRating = 0;

    let realm;
    try {

        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Locations"), {
                name: "all-locations",
                update: true,
            });
        });

        let newLocation;
        realm.write(() => {
            const newId = new Realm.BSON.ObjectId();
            newLocation = realm.create(Locations, {
                _id: newId,
                _idString: newId.toString(),
                locationName: locationName,
                address: address,
                city: city,
                state: state,
                zipCode: zipCode,
                averageRating: averageRating
            });
        });

        res.json({
            status: "SUCCESS",
            message: "Locations doc created successfully",
            data: newLocation
        });

    } catch (err) {
        // If an error occurs, return an error message with an error code
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while creating an Locations doc",
            data: err,
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm);
    }

});

router.post('/updateRating', async (req, res) => {
    let { locationId, addedRating } = req.body;

    let realm;
    try {

        addedRating = Number(addedRating);

        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Locations"), {
                name: "all-locations",
                update: true,
            });
        });

        const location = realm.objects("Locations").filtered("_idString == $0", locationId)[0];
        
        const currentAverageRating = location["averageRating"];
        averageRating = currentAverageRating + addedRating;

        if (!location) {
            return res.status(400).json({
                status: "FAILED",
                message: "Location not found"
            });
        }
        
        realm.write(() => {
            location["averageRating"] = averageRating;
        });
        

        res.json({
            status: "SUCCESS",
            message: "Location reviews updated successfully"
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

router.get('/getAllLocations', async (req, res) => {

    let realm;
    try {
        // Open the realm
        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Locations"), {
                name: "Locations-data",
                update: true,
            });
        });

        const locationsCollection = realm.objects(Locations);
        const locationsArray = Array.from(locationsCollection);

        res.json({
            status: "SUCCESS",
            message: "Locations data retrieved successfully",
            data: locationsArray
        });

    } catch (err) {
        // Handle any errors that occur during retrieval
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while retrieving the Locations data",
            error: err.message
        });
    } finally {
        if (realm)
            realm = await closeRealm(realm);
    }


});

module.exports = router;