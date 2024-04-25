const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const credentialsSchema = new Schema({
    email: { type: String, required: true },
    username: { type: String, required: true },
    password: { type: String, required: true },
    verified: { type: Boolean, default: false}
});

const Credentials = mongoose.model('Credentials', credentialsSchema, "Credentials");

module.exports = Credentials;