package com.example.test10_12_jjh.test12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test10_12_jjh.databinding.ActivitySixthPracticeLoginBinding

class SixthPracticeLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySixthPracticeLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.backmain.setOnClickListener {
//            val intent : Intent = Intent(this@SixthPracticeLogin, SixthPractice::class.java)
//            startActivity(intent)
//        }

        binding.backmain.setOnClickListener {
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.loginBtn.setOnClickListener {
            val id : String = binding.id.text.toString()
            val pw : String = binding.password.text.toString()
            val intent : Intent = Intent(this@SixthPracticeLogin, SixthPractice::class.java)

            Toast.makeText(this@SixthPracticeLogin, "id : $id, pw : $pw", Toast.LENGTH_SHORT).show()

            startActivity(intent)
        }
    }
}