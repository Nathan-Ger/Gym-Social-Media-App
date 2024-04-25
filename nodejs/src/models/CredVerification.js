const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const CredVerificationSchema = new Schema({
    userId: { type: String, required: true },
    uniqueSting: { type: String, required: true },
    createdAt: { type: Date, required: true },
    expiresAt: { type: Date, required: true }
});

const CredVerification = mongoose.model('CredVerification', CredVerificationSchema, "CredVerification");

module.exports = Credentials;