const Realm = require('realm');

class Locations extends Realm.Object {
    static schema = {
        name: "Locations",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            locationName: "string",
            address: "string",
            city: "string",
            state: "string",
            zipCode: "string",
            averageRating: {type: "double", default: 0},
            reviews: "Locations_reviews[]?",
        },
    };
}

module.exports = Locations;
