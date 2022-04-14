package com.example.sdgp_silent_voice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // create instances from buttons and texfields
        var signupBtn = findViewById<Button>(R.id.signup_button);

        var fNameText = findViewById<TextView>(R.id.firstName);
        var lNameText = findViewById<TextView>(R.id.lastName);
        var usernameText = findViewById<TextView>(R.id.username);
        var mailText = findViewById<TextView>(R.id.email);
        var passwordText = findViewById<TextView>(R.id.password);
        var confPasswordText = findViewById<TextView>(R.id.confPassword);

        // set up function to signup button click
        signupBtn.setOnClickListener {
            // Capturing the values entered by the user
            var fName = fNameText.text.toString();
            var lName = lNameText.text.toString();
            var username = usernameText.text.toString();
            var mail = mailText.text.toString();
            var password = passwordText.text.toString();
            var confPassword = confPasswordText.text.toString();

            // User input validations
            if(fName.isEmpty()){
                fNameText.error = "First name can not be empty!";
                fNameText.requestFocus();
                //return@setOnClickListener;

                Log.i("First Name:", "First name can not be empty!");
            }
            if(lName.isEmpty()){
                lNameText.error = "Last name can not be empty!";
                lNameText.requestFocus();
                //return@setOnClickListener;

                Log.i("last Name:", "Last name can not be empty!");
            }
            if(username.isEmpty()){
                usernameText.error = "Username can not be empty!";
                usernameText.requestFocus();
                return@setOnClickListener;

                Log.i("Username:", "Username can not be empty!");
            }
            if(mail.isEmpty()){
                mailText.error = "Email can not be empty!";
                mailText.requestFocus();
                return@setOnClickListener;

                Log.i("Email", "Email can not be empty!");
            }
            if(password.isEmpty()){
                passwordText.error = "Password can not be empty!";
                passwordText.requestFocus();
                return@setOnClickListener;

                Log.i("Password", "Password can not be empty!");
            }
            if(confPassword.isEmpty()){
                confPasswordText.error = "Password can not be empty!";
                confPasswordText.requestFocus();
                return@setOnClickListener;

                Log.i("Password", "Password can not be empty!");
            }
            else{
                if (password != confPassword) {
                    confPasswordText.error = "Confirm password does not match with the password you have entered!";
                    confPasswordText.requestFocus();
                    return@setOnClickListener;

                    Log.i("Password validation: ", "Confirm password does not match with the password you have entered!")
                }
                else{
                    var newUser = "{\"firstName\": \"$fName\",\"lastName\": \"$lName\",\"userName\": \"$username\",\"email\": \"$mail\",\"encryptedPassword\": \"$password\"}";

                    // new coroutine to send the request
                    runBlocking {
                        launch {
                            sendReq(newUser);                                                       // call the method to send the reques
                        }
                    }
                }
            }
        };
    }

    /**
     * Function to build and send the request
     *
     * @param user Request body including the details of the user
     */
    private fun sendReq(user: String){
        val client = OkHttpClient();                                                                // Instance from the OkHttpClient library

        val req = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), user); // create request body

        val request = Request.Builder()                                                             // Build request
            //.url("http://localhost:5000/api/signup")                                              // Localhost URL
            .url("http://172.20.10.10:5000/api/signup")                                           // IP address from Charaka's mobile hotspot
            //.url("http://192.168.8.197:5000/api/signup")                                          // IP address from Charaka's home wifi
            //.url("http://192.168.8.163:5000/api/signup")                                        // IP address from office
            .method("POST", req)
            .header("Content-Type", "application/json")
            .build();

        client.newCall(request).enqueue(object : Callback {                                         // send the request
            // function to fire if the request was unable to send
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Response:", "-------------------- request failed --------------------");

                Log.d("IOException: ", e.message.toString() );
                Log.d("Exception: ", "If failed to connect to the given ip address in the request url, find your current ip address and add it to the network_security.xml file and the request url.");
            };

            // function to fire is the request was sent and the response was received
            override fun onResponse(call: Call, res: Response) {
                Log.d("Response:", "-------------------- response received --------------------");

                val message = res.body()?.string();                                                   // converting response body to a String

                // check whether the response is empty or not
                if (message != null) {
                    Log.d("Confirmation Message:", message);

                    if(message == "email_exists"){
                        Log.d("Status: ", "This email is already registered!");

                        emailText.error = "This email is already registered!";
                        emailText.requestFocus();
                    }
                    if(message == "username_exists"){
                        Log.d("Status: ", "This username is already taken!");

                        passwordText.error = "This username is already taken!";
                        passwordText.requestFocus()
                    }
                    if(message == "user_added"){
                        Log.d("Status: ", "User added successfully!");

                        //call intent to load camera activity
                    }
                };
            }
        })
    }
}