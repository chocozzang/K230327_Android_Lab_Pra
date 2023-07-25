package com.example.test10_12_jjh.test12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test10_12_jjh.databinding.ActivitySixthPracticeBinding

class SixthPractice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySixthPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginPage.setOnClickListener {
            val intent : Intent = Intent(this@SixthPractice, SixthPracticeLogin::class.java)
            startActivity(intent)
        }

        binding.registerPage.setOnClickListener {
            val intent : Intent = Intent(this@SixthPractice, SixthPracticeRegister::class.java)
            startActivity(intent)
        }
    }
}