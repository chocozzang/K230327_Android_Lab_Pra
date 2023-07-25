package com.example.test13_16_17_18.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.test13_16_17_18.R

class SixthPracticeIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth_practice_intro)

        Handler().postDelayed(Runnable {
            val intent = Intent(this@SixthPracticeIntro, SixthPractice::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}