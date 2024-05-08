const Realm = require('realm');

/* Exercise schema for MongoDB Realm
* @author Nathanael Germain
*
* Schema matches that in MongoDB Realm and Java Models
*/
class Exercise extends Realm.Object {
    static schema = {
        name: "Exercise",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()}, // Primary key, generated in code.
            _idString: "string", // String version of the primary key, this allows us to search it, ObjectId is not searchable.
            email: "string", // Email of the user who did the exercise, allows us to associate data with a user, Realm automatically relates this so the logged in users email has to match this.
            exerciseName: "string", // Name of the exercise, e.g. Bench Press, Squat, Deadlift, etc.
            lifted: "bool", // True if the exercise is a weight lifting exercise, false if it is a cardio/swimming exercise.
            weight: "double?", // Weight lifted, only used if lifted is true.
            sets: "int?", // Number of sets, only used if lifted is true.
            timeSpent: "double?", // Time spent on the exercise.
            dateOfWorkout: "date?", // Date of the workout.
            caloriesBurned: "double?", // Calories burned during the workout.
            miles: "double?", // Miles ran/swam, only used if lifted is false.
        },
    };
}

module.exports = Exercise;