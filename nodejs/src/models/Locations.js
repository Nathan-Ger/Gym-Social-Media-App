const Realm = require('realm');

class Locations extends Realm.Object {
    static schema = {
        name: "Locations",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            _idString: "string",
            locationName: "string",
            address: "string",
            city: "string",
            state: "string",
            zipCode: "string",
            averageRating: "double?",
        },
    };
}

module.exports = Locations;
