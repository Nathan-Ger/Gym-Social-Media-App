const Realm = require('realm');

/* User schema for MongoDB Realm
* @author Nathanael Germain
*
* Schema matches that in MongoDB Realm and Java Models
*/
class User extends Realm.Object {
    static schema = {
        name: "User",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId() }, // Primary key
            fName: "string?", // First name
            lName: "string?", // Last name
            username: "string", // Username
            email: "string", // Email, associates user with logged in account
            phoneNumber: "string?", // Phone number
            birthday: "date?", // Birthday
            startingWeight: "double?", // Starting weight
            currentWeight: "double?", // Current weight
            goalWeight: "double?", // Goal weight
            height: "double?", // Height
            profilePicture: "string?", // Profile picture
            createdAt: "date?", // Date the user was created
            totalTimeInGym: "double?", // Total time spent in the gym
            totalCaloriesBurned: "double?", // Total calories burned
        },
    };
}

module.exports = User;