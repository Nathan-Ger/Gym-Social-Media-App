const Realm = require('realm');

class Exercise extends Realm.Object {
    static schema = {
        name: "Exercise",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            _idString: "string",
            email: "string",
            exerciseName: "string",
            lifted: "bool",
            weight: "double?",
            sets: "int?",
            timeSpent: "double?",
            dateOfWorkout: "date?",
            caloriesBurned: "double?",
            miles: "double?",
        },
    };
}

module.exports = Exercise;