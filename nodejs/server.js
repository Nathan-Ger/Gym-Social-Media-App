//require('./src/config/db.js');

const app = require('express')();
const port = 3000;

const cors = require('cors');
app.use(cors());

const CredRouter = require('./src/routes/Credentials.js');

const bodyParser = require('express').json;
app.use(bodyParser());

app.use('/Credentials', CredRouter);

app.listen(port, () => {
    console.log(`Server running on port ${port}`);
})
