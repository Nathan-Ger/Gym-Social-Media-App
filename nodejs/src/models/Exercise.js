const Realm = require('realm');

class Exercise extends Realm.Object {
    static schema = {
        name: "Exercise",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            username: {type: "string", required: true},
            exerciseName: {type: "string", required: true},
            lifted: {type: Boolean, required: true},
            weight: "double",
            sets: "int",
            timeSpent: "double",
            dateOfWorkout: {type: "date", default: () => new Date()},
            caloriesBurned: "double",
            miles: "double",
        },
        primaryKey: "_id",
    };
}

module.exports = Exercise;