package com.example.sdgp_silent_voice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_log_in.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        // create instances from buttons and texfields
        var loginBtn = findViewById<Button>(R.id.login_button);

        var emailText = findViewById<TextView>(R.id.emailText);
        var passwordText = findViewById<TextView>(R.id.passwordText);

        // set up function to login button click
        loginBtn.setOnClickListener {
            // Capturing the values entered by the user
            var mail = emailText.text.toString();
            var password = passwordText.text.toString();

            // User input validations
            if(mail.isEmpty()){
                emailText.error = "Email can not be empty!";
                emailText.requestFocus();
                return@setOnClickListener;

                Log.i("Email:", "Email can not be empty!");
            }
            if(password.isEmpty()){
                passwordText.error = "Password can not be empty!";
                passwordText.requestFocus();
                return@setOnClickListener;

                Log.i("Password: " , "Password can not be empty");
            }
            else{
                var user = "{\"email\": \"$mail\", \"encryptedPassword\": \"$password\"";

                sendReq(user);
            }
        };
    }

    /**
     * Function to build and send the request
     *
     * @param user Request body including the email and the password of the user
     */
    private fun sendReq(user: String){
        val client = OkHttpClient();                                                                // Instance from the OkHttpClient library

        val req = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), user); // create request body

        val request = Request.Builder()                                                             // Build request
            //.url("http://localhost:5000/api/login")                                               // Localhost URL
            //.url("http://172.20.10.10:5000/api/login")                                            // IP address from Charaka's mobile hotspot
            //.url("http://192.168.8.197:5000/api/login")                                           // IP address from Charaka's home wifi
            .url("http://192.168.8.163:5000/api/login")                                         // IP address from office
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

                    val resObj = JSONObject(message);                                               // converting response String to a jason object

                    if(message == "email_not_reg"){
                        Log.d("Status: ", "This email is not registered!");

                        emailText.error = "This email is not registered!";
                        emailText.requestFocus();
                    }
                    if(message == "password_not_match"){
                        Log.d("Status: ", "Password is incorrect!");

                        passwordText.error = "Password is incorrect!";
                        passwordText.requestFocus();
                    }
                    if(message == "login_success"){
                        Log.d("Status: ", "Logged in successfully!");

                        //code to load camera activity
                    }
                };
            }
        })
    }
}