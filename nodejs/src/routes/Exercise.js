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
const Exercise = require('../models/Exercise');

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

        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Exercise"), {
                name: "all-exercises",
                update: true,
            });
        });

        let newExercise;
        realm.write(() => {
            newExercise = realm.create(Exercise, {
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
        // If an error occurs, return an error message with an error code
        res.status(500).json({
            status: "FAILED",
            message: "An error occurred while creating an exercise doc",
            data: err,
            error: err.message
        });
    } finally {
        realm = await closeRealm(realm);
    }

});

router.post('/updateExercise', async (req, res) => {
    let { exerciseId, field, newValue } = req.body;

    field = field.toLowerCase();

    let realm;
    try {

        realm = await openRealm(app.currentUser);

        await realm.subscriptions.update((mutableSubs) => {
            mutableSubs.add(realm.objects("Exercise"), {
                name: "all-exercises",
                update: true,
            });
        });

        const exercise = realm.objects("Exercise").filtered("_idString == $0", exerciseId)[0];

        if (!exercise) {
            return res.status(400).json({
                status: "FAILED",
                message: "Exercise not found"
            });
        }

        // None of the below should ever be changed.
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
        realm = await closeRealm(realm);
    }

});

// Parses an date string into a Date object using the format MMDDYYYY
function parseDate(dateString) {
    const month = dateString.substring(0, 2) - 1;
    const day = dateString.substring(2, 4);
    const year = dateString.substring(4, 8);
    return new Date(year, month, day);
}

module.exports = router;