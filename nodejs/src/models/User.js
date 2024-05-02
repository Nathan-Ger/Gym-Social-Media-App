const Realm = require('realm');

class User extends Realm.Object {
    static schema = {
        name: "User",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId() },
            fName: "string?",
            lName: "string?",
            username: "string",
            email: "string",
            phoneNumber: "string?",
            birthday: "date?",
            startingWeight: "double?",
            currentWeight: "double?",
            goalWeight: "double?",
            height: "double?",
            profilePicture: "string?",
            createdAt: "date?",
            totalTimeInGym: "double?",
            totalCaloriesBurned: "double?",
        },
    };
}

module.exports = User;