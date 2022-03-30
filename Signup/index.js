const express = require("express");     // create an instance of express framework
const cors = require("cors");           // create an instance of connect/express middleware
const mongoose = require("mongoose");   // create an instance of mongodb ODM library to communicate with mongoDB

const router = require("./routes");

const app = express();                  // initialize a new express app

require("dotenv").config();             // configure environment variable

const PORT = process.env.PORT || 5000;  // create a port

const uri = process.env.ATLAS_URI;      // create the URI to connect to the database

// connect to the databse
mongoose.connect(
    uri,
    {
        useNewUrlParser: true,
        useUnifiedTopology: true,
        //useCreateIndex: true
    }).then(() => {
        console.log("Connected to the database");
});

app.use(cors());                        // use middleware to allow cross origins
app.use(express.json());                // grants permission to access request body

app.use("/api", router);                // set routing path    

//set up port
app.listen(PORT, () => {
    console.log("Listening to port " + PORT);
});