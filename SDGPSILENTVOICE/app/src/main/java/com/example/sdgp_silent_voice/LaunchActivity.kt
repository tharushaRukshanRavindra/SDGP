package com.example.sdgp_silent_voice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

//        val lottie = findViewById<LottieAnimationView>(R.id.lottie)
//        val logo = findViewById<ImageView>(R.id.imageView)
//        val text = findViewById<TextView>(R.id.textViewLauncher1)


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 5000)
    }
}