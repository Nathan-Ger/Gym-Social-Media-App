// Import express and set up the router
const express = require('express');
const router = express.Router();

// Import the MongoDB Realm SDK and the utilites functions
const { MongoClient, ObjectId } = require('mongodb');
const { openRealm, closeRealm } = require('../utils/realmUtils.js');

// Import and initialize the MongoDB Realm SDK
const Realm = require('realm');
const app = new Realm.App({ id: 'gymsocialbefit-rzkqhmz' });

// Import the User model
const Locations = require('../models/Locations');

/**
 * Locations.js
 * @author Nathanael Germain
 *
 * This file contains the routes for creating, updating, and retrieving location data.
 */

router.post('/createLocation', async (req, res) => {
    let { locationName, address, city, state, zipCode} = req.body;

    // Below field are not required.
    let averageRating = 0;

    let realm;
    try {
        realm = await openRealm(app.currentUser); // Opens realm for user

        // Open a subscription to the Locations collection, required to access the data in Realm.
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Locations"), {
                name: "all-locations",
                update: true,
            });
        });

        // Create a new Locations object in the realm
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
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while creating an Locations doc",
            data: err,
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm); // Close the realm
    }

});

router.post('/updateRating', async (req, res) => {
    let { locationId, addedRating } = req.body;

    let realm;
    try {
        addedRating = Number(addedRating); // Make sure the rating is a number

        realm = await openRealm(app.currentUser); // Opens realm for user

        // Open a subscription to the Locations collection, required to access the data in Realm.
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Locations"), {
                name: "all-locations",
                update: true,
            });
        });

        // Get the location object from the realm
        const location = realm.objects("Locations").filtered("_idString == $0", locationId)[0];
        
        // Update the average rating
        const currentAverageRating = location["averageRating"];
        averageRating = currentAverageRating + addedRating;
        // TODO: Add logic to update the average rating based on the number of reviews

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
        realm = await closeRealm(realm); // Close the realm
    }

});

router.get('/getAllLocations', async (req, res) => {

    let realm;
    try {
        realm = await openRealm(app.currentUser); // Opens realm for user

        // Open a subscription to the Locations collection, required to access the data in Realm.
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Locations"), {
                name: "Locations-data",
                update: true,
            });
        });

        // Get all the locations from the realm
        const locationsCollection = realm.objects(Locations);
        const locationsArray = Array.from(locationsCollection);

        res.json({
            status: "SUCCESS",
            message: "Locations data retrieved successfully",
            data: locationsArray
        });

    } catch (err) {
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while retrieving the Locations data",
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm); // Close the realm
    }


});

module.exports = router;