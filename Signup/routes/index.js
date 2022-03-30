const express = require("express");                             // create an instance of express framework

const router = express.Router();                                // create new router object

const { signup, login } = require("../controllers/userController");    // declare routing path

router.post("/signup", signup);                                 // set up routing path for signup

router.post("/login", login);                                   // set up routing path for login

module.exports = router;                                        // grant permission to access this file from other files