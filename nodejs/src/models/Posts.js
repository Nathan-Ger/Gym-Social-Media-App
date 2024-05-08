const Realm = require('realm');

/* Posts schema for MongoDB Realm
* @author Nathanael Germain
*
* Schema matches that in MongoDB Realm and Java Models
*/
class Posts extends Realm.Object {
    static schema = {
        name: "Posts",
        primaryKey: "_id",
        properties: {
            _id: { type: "objectId", default: () => new Realm.BSON.ObjectId()}, // Primary key, generated in code.
            _idString: "string", // String version of the primary key, this allows us to search it, ObjectId is not searchable.
            email: "string", // Email of the user who made the post, allows us to associate data with a user, Realm automatically relates this so the logged in users email has to match this.
            mediaLink: "string", // Link to the media, e.g. image, video, etc.
            caption: "string?", // Caption of the post.
            createdAt: "date?", // Date the post was created.
            likes: "int?", // Number of likes on the post.
        },
    };
}

module.exports = Posts;