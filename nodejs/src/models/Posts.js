const Realm = require('realm');

class Posts extends Realm.Object {
    static schema = {
        name: "Posts",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            _idString: "string",
            email: "string",
            mediaLink: "string",
            caption: "string?",
            createdAt: "date?",
            likes: "int?",
        },
    };
}

module.exports = Posts;