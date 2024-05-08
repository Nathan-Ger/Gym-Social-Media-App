const Realm = require('realm');

class Reviews extends Realm.Object {
    static schema = {
        name: "Reviews",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            _idString: "string",
            email: "string",
            rating: "double",
            review: "string?",
            createdAt: "date?",
            location_id: 'string',
        },
    };
}

module.exports = Reviews;