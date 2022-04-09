package com.example.sdgp_silent_voice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signUpBtn: Button = findViewById(R.id.signupBtn)
        val logInBtn: Button = findViewById(R.id.loginBtn)
        val skipBtn: Button = findViewById(R.id.skipBtn)


        signUpBtn.setOnClickListener {
            val i = Intent(this, SignupActivity::class.java )
            startActivity(i)
        }

        logInBtn.setOnClickListener {
            val  i = Intent(this, LogInActivity::class.java)
            startActivity(i)
        }

        skipBtn.setOnClickListener {
            val i = Intent(this, MainActivity2::class.java)
            startActivity(i)
        }


    }
}