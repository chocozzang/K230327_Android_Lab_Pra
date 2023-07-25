package com.example.test10_12_jjh.test12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

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