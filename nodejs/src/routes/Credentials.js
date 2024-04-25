const express = require('express');
const router = express.Router();

const Credentials = require('../models/Credentials.js');

const bcrypt = require('bcrypt');

router.post('/signup', (req, res) => {
    let { email, username, password } = req.body;
    email = email.trim();
    email = email.toLowerCase();
    username = username.trim();
    username = username.toLowerCase();
    password = password.trim();

    if (email == "" || username == "" || password == "") {
        res.json({
            status: "FAILED",
            message: "Empty input fields!"
        });
    } else if (!/^[a-zA-Z ]+$/.test(username)) {
        res.json({
            status: "FAILED",
            message: "Invalid username format!"
        });
    } else if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email)) {
        res.json({
            status: "FAILED",
            message: "Invalid email format!"
        });
    } else if (password.length < 8) {
        res.json({
            status: "FAILED",
            message: "Password is too short!"
        });
    } else {
        Credentials.find({email}).then((result) => {
            if (result.length) {
                res.json({
                    status: "FAILED",
                    message: "Email already exists!"
                });
            } else {
                Credentials.find({username}).then((result) => {
                    if (result.length) {
                        res.json({
                            status: "FAILED",
                            message: "Username already exists!"
                        });
                    } else {
                        const saltRounds = 10;
                        bcrypt.hash(password, saltRounds).then((hashedPassword) => {
                            const newUser = new Credentials({
                                email,
                                username,
                                password: hashedPassword
                            });
                            newUser.save().then(result => {
                                res.json({
                                    status: "SUCCESS",
                                    message: "User created successfully!"
                                });
                            }).catch(err => {
                                res.json({
                                    status: "FAILED",
                                    message: "An error occurred at signup!"
                                });
                            })
                        })
                        .catch((err) => {
                            console.log(err);
                            res.json({
                                status: "FAILED",
                                message: "An error occurred at hashing password!"
                            });
                        })

                    }
                }).catch((err) => {
                    console.log(err);
                    res.json({
                        status: "FAILED",
                        message: "An error occurred!"
                    });
                })
            }
        }).catch((err) => {
            console.log(err);
            res.json({
                status: "FAILED",
                message: "An error occurred!"
            })
        })

        
    }
})

router.post('/signin', (req, res) => {

    let { email, password } = req.body;
    email = email.trim();
    email = email.toLowerCase();
    password = password.trim();

    if (email == "" || password == "") {
        res.json({
            status: "FAILED",
            message: "Empty input fields!"
        });
    } else {
        Credentials.find({email}).then((data) => {
            if (data.length) {
                const hashedPassword = data[0].password;
                bcrypt.compare(password, hashedPassword).then((result) => {
                    if (result) {
                        res.json({
                            status: "SUCCESS",
                            message: "User is authenticated!",
                            data: data
                        });
                    } else {
                        res.json({
                            status: "FAILED",
                            message: "Authentication failed!"
                        });
                    }
                }).catch((err) => {
                    console.log(err);
                    res.json({
                        status: "FAILED",
                        message: "Incorrect Password!"
                    });
                })
            } else {
                res.json({
                    status: "FAILED",
                    message: "User not found!"
                });
            }
        }).catch((err) => {
            res.json({
                status: "FAILED",
                message: "An error occurred!"
            });
        })
    }

})

module.exports = router;