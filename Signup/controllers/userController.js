const User = require("../model/User");      // create an instance from user model 

const bcrypt = require("bcrypt");           // create an instance of bcrypt hashing function

// check data and map data to the schema
exports.signup = async (req, res) => {
    let { firstName, lastName, userName, email, encryptedPassword } = req.body;     // assigning data into variables
    
    const securePassword = await bcrypt.hash(encryptedPassword, 10);                // hashing the password
    
    const user = { 
        firstName,
        lastName,
        userName,
        email,
        encryptedPassword: securePassword
    };

    // check whether username or email already exists
    User.findOne({ $or: [
        { email },
        { userName }
    ] }).then((foundUser) => {
        if(foundUser) {
            if(foundUser.email == email) {
                return res.status(409).send("email already exists");
            }
            if(foundUser.userName == userName) {
                return res.status(409).send("username already exists");
            }
        } else {
            const NewUser = new User(user);         // create new user objcet

            // save created user object in the databse
            NewUser.save().then(() => {
                return res.send("User added successfully!");
            }).catch(err => {
                return res.send(err);
            });
        };
    });   
};

exports.login = async (req, res) => {
    let { email, encryptedPassword } = req.body;

    User.findOne({ email }).then((foundUser) => {
        if(foundUser.email == email){
            bcrypt.compare(encryptedPassword, foundUser.encryptedPassword, (err, response) => {
                if(response == true){
                    res.status(200).send("login succesfull!");
                }
                else{
                    res.status(404).send("password does not match!");
                }
            });
        }
        else{
            res.status(404).send("email does not exists!");
        }
    });
};