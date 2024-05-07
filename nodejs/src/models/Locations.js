const Realm = require('realm');
const { primaryKey } = require('./Exercise');

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
            posts: {type: "Reviews[]", default: [] },
        },
    };
}

class Reviews extends Realm.Object {
    static schema = {
        name: "Reviews",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            email: "string",
            rating: "double",
            review: "string?",
            createdAt: {type: "date", default: () => new Date()},
            location: "Locations",
        },
    };
}

module.exports = { Locations, Reviews };
