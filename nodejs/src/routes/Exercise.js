// Import express and set up the router
const express = require('express');
const router = express.Router();

// Import the MongoDB Realm SDK and the Exercise model
const { MongoClient, ObjectId } = require('mongodb');
const { openRealm, closeRealm } = require('../utils/realmUtils.js');

// Import and initialize the MongoDB Realm SDK
const Realm = require('realm');
const app = new Realm.App({ id: 'gymsocialbefit-rzkqhmz' });

// Import the User model
const Exercise = require('../models/Exercise');

/**
 * Exercise.js
 * @author Nathanael Germain
 *
 * This file contains the routes for creating, updating, and retrieving exercise data.
 */

router.post('/createExercise', async (req, res) => {
    let { exerciseName, lifted, weight, sets, timeSpent, dateOfWorkout, caloriesBurned, miles } = req.body;

    // Below field are not required.
    lifted = lifted || false;
    weight = weight || 0;
    sets = sets || 0;
    timeSpent = timeSpent || 0;
    dateOfWorkout = dateOfWorkout || new Date();
    caloriesBurned = caloriesBurned || 0;
    miles = miles || 0;

    let realm;
    try {

        realm = await openRealm(app.currentUser); // Opens realm for user

        // Open a subscription to the Exercise collection, required to access the data in Realm.
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Exercise"), {
                name: "all-exercises",
                update: true,
            });
        });

        // Create a new Exercise object in the realm
        let newExercise;
        realm.write(() => {
            const newId = new Realm.BSON.ObjectId();
            newExercise = realm.create(Exercise, {
                _id: newId,
                _idString: newId.toString(),
                email: app.currentUser.profile.email,
                exerciseName: exerciseName,
                lifted: lifted,
                weight: weight,
                sets: sets,
                timeSpent: timeSpent,
                dateOfWorkout: dateOfWorkout,
                caloriesBurned: caloriesBurned,
                miles: miles
            });
        });

        res.json({
            status: "SUCCESS",
            message: "Exercise doc created successfully",
            data: newExercise
        });

    } catch (err) {
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while creating an exercise doc",
            data: err,
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm); // Closes realm for user after operation
    }

});

router.post('/updateExercise', async (req, res) => {
    let { exerciseId, field, newValue } = req.body;

    field = field.toLowerCase(); // Convert field to lowercase for consistency

    let realm;
    try {

        realm = await openRealm(app.currentUser); // Open realm for user

        // Open a subscription to the Exercise collection, required to access the data in Realm.
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Exercise"), {
                name: "all-exercises",
                update: true,
            });
        });

        // Find the exercise object in the realm based off of the passed id.
        const exercise = realm.objects("Exercise").filtered("_idString == $0", exerciseId)[0];

        if (!exercise) {
            return res.status(400).json({
                status: "FAILED",
                message: "Exercise not found"
            });
        }

        // email should ever be changed.
        if (field == "email") {
            return res.status(400).json({
                status: "FAILED",
                message: field + " cannot be updated"
            });
        }

        if (field == "dateOfWorkout") {
            dateOfWorkout = parseDate(newValue);
            realm.write(() => {
                exercise[field] = dateOfWorkout;
            });
        }

        // All edits outside of above conditions
        realm.write(() => {
            if (exercise[field] != undefined) {
                exercise[field] = newValue;
            }
        });

        res.json({
            status: "SUCCESS",
            message: "Exercise " + field + " updated successfully"
        });
    } catch (error) {
        res.status(500).json({
            status: "FAILED",
            message: "Error updating Realm object",
            error: error.message
        });
    } finally {
        realm = await closeRealm(realm); // Close realm for user after operation
    }

});

router.get('/getAllExercises', async (req, res) => {

    const userEmail = app.currentUser.profile.email; // Get the current user's email

    let realm;
    try {
        realm = await openRealm(app.currentUser); // Open realm for user

        // Open a subscription to the Exercise collection, required to access the data in Realm.
        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Exercise"), {
                name: "exercise-data",
                update: true,
            });
        });

        // Retrieve all exercises for the current user, puts it in an array.
        const exerciseCollection = realm.objects(Exercise);
        const exercises = exerciseCollection.filtered('email == $0', userEmail);
        const exercisesArray = Array.from(exercises);

        res.json({
            status: "SUCCESS",
            message: "Current user's exercise data retrieved successfully",
            data: exercisesArray
        });

    } catch (err) {
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while retrieving the user's exercise data",
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm); // Close realm for user after operation
    }


});

// Parses a date string into a Date object using the format MMDDYYYY
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