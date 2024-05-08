const Realm = require('realm');

/* Reviews schema for MongoDB Realm
* @author Nathanael Germain
*
* Schema matches that in MongoDB Realm and Java Models
*/
class Reviews extends Realm.Object {
    static schema = {
        name: "Reviews",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()}, // Primary key, generated in code.
            _idString: "string", // String version of the primary key, this allows us to search it, ObjectId is not searchable.
            email: "string", // Email of the user who made the review, allows us to associate data with a user, Realm automatically relates this so the logged in users email has to match this.
            rating: "double", // Rating of the location.
            review: "string?", // Review of the location.
            createdAt: "date?", // Date the review was created.
            location_id: 'string', // Location ID of the location the review is for.
        },
    };
}

module.exports = Reviews;