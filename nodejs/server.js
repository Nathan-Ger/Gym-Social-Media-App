/**
 * server.js
 * @author Nathanael Germain
 * This script sets up an Express server that listens on a range of ports (3000-3100).
 * If a port is in use, it tries the next one. It also sets up CORS and JSON parsing,
 * Sets up a route for handling credential-related requests.
 */

// Sets up Express app along with CORS and JSON parsing
const app = require('express')();
const cors = require('cors');
app.use(cors());
const bodyParser = require('express').json;
app.use(bodyParser());

// Sets up the route for handling credential-related requests (logging in and signing up)
const CredRouter = require('./src/routes/Credentials.js');
app.use('/Credentials', CredRouter);

// Sets up the route for handling user-related requests (search, modify, etc functions)
const UserRouter = require('./src/routes/User.js');
app.use('/User', UserRouter);

const ExerciseRouter = require('./src/routes/Exercise.js');
app.use('/Exercise', ExerciseRouter);

const PostsRouter = require('./src/routes/Posts.js');
app.use('/Posts', PostsRouter);

// Defined port range.
const startPort = 3000;
const endPort = 3100;
let currentPort = startPort;

// Sets up the server to listen on the first available port in the range.
const server = app.listen(currentPort, () => console.log(`${currentPort}`));
server.on('error', (error) => {
    if (error.code === 'EADDRINUSE' && currentPort < endPort) {
        currentPort++;
        server.close(() => {
            server = app.listen(currentPort, () => console.log(`${currentPort}`));
        });
    } else {
        console.error(error);
    }
});

