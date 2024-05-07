const Realm = require('realm');

class Posts extends Realm.Object {
    static schema = {
        name: "Posts",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            email: "string",
            mediaLink: "string",
            caption: "string?",
            createdAt: {type: "date", default: () => new Date()},
            likes: { type: "int", default: 0 },
        },
    };
}

module.exports = Posts;