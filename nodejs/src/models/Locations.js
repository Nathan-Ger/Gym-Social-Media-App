const Realm = require('realm');

class Locations extends Realm.Object {
    static schema = {
        name: "Locations",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            locationName: {type: "string", required: true},
            address: {type: "string", required: true},
            city: {type: "string", required: true},
            state: {type: "string", required: true},
            zipCode: {type: "string", required: true},
            averageRating: {type: "double", default: 0},
            posts: "Reviews[]",
        },
        primaryKey: "_id",
    };
}

class Reviews extends Realm.Object {
    static schema = {
        name: "Reviews",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            username: {type: "string", required: true},
            rating: {type: "double", required: true},
            review: "string",
            createdAt: {type: "date", default: () => new Date()},
            location: "Locations",
        },
        primaryKey: "_id",
    };
}


module.exports = { Locations, Reviews };
