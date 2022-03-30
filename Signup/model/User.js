const mongoose = require("mongoose");       // create an instance of mongodb ODM library to communicate with mongoDB

// set up schema to define the structure of the data 
const UserSchema = new mongoose.Schema({
    firstName:{
        type: String,
        required: true,
        maxlength: 32,
        trim: true
    },
    lastName:{
        type: String,
        required: true,
        maxlength: 32,
        trim: true
    },
    userName:{
        type: String,
        required: true,
        maxlength: 32,
        trim: true
    },
    email:{
        type: String,
        required: true,
        maxlength: 32,
        trim: true
    },
    encryptedPassword:{
        type: String,
        required: true,
        trim: true
    }
},{
    timestamps: true
});

module.exports = mongoose.model("User", UserSchema);    // grant permission to access this file from other files