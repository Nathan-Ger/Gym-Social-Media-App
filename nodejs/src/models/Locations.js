const Realm = require('realm');

/* Locations schema for MongoDB Realm
* @author Nathanael Germain
*
* Schema matches that in MongoDB Realm and Java Models
*/
class Locations extends Realm.Object {
    static schema = {
        name: "Locations",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()}, // Primary key, generated in code.
            _idString: "string", // String version of the primary key, this allows us to search it, ObjectId is not searchable.
            locationName: "string", // Name of the location.
            address: "string", // Address of the location.
            city: "string", // City of the location.
            state: "string", // State of the location.
            zipCode: "string", // Zip code of the location.
            averageRating: "double?", // Average rating of the location.
        },
    };
}

module.exports = Locations;
