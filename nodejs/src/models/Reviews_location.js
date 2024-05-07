const Realm = require('realm');

class Reviews_location extends Realm.Object {
    static schema = {
        name: "Reviews_location",
        embedded: true,
        properties: {},
    };
}

module.exports = Reviews_location;