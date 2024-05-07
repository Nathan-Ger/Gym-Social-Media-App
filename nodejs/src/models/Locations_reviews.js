const Realm = require('realm');

class Locations_reviews extends Realm.Object {
    static schema = {
        name: "Locations_reviews",
        embedded: true,
        properties: {},
    };
}

module.exports = Locations_reviews;