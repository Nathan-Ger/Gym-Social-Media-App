/*require('dotenv').config();
const mongoose = require('mongoose');

mongoose
    .connect(process.env.MONGO_URI, {
        useNewUrlParser: true,
        useUnifiedTopology: true
    })
    .then(() => {
        console.log('Connected to database');
    })
    .catch((err: any) => console.log(err));*/

import dotenv from 'dotenv';
import mongoose, { ConnectOptions } from 'mongoose';

dotenv.config();

interface CustomConnectOptions extends ConnectOptions {
    useNewUrlParser?: boolean;
    useUnifiedTopology?: boolean;
}

const options: CustomConnectOptions = {
    useNewUrlParser: true,
    useUnifiedTopology: true,
};

mongoose
    .connect(process.env.MONGO_URI as string, options)
    .then(() => {
        console.log('Connected to database');
    })
    .catch((err: Error) => console.log(err));

//export default connectDB;
