const Realm = require('realm');

class Posts extends Realm.Object {
    static schema = {
        name: "Posts",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()},
            username: {type: "string", required: true},
            mediaLink: {type: "string", required: true},
            caption: "string",
            createdAt: {type: "date", default: () => new Date()},
            likes: "int",
        },
        primaryKey: "_id",
    };
}

module.exports = Posts;