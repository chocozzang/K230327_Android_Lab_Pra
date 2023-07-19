package com.example.test6_jjh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test6_jjh.databinding.ActivitySixthPracticeRegisterBinding

class SixthPracticeRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySixthPracticeRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {
            val intent : Intent = Intent(this@SixthPracticeRegister, this@SixthPracticeRegister::class.java)
            val intent2 : Intent = Intent(this@SixthPracticeRegister, SixthPracticeLogin::class.java)

            val id   : String = binding.id.text.toString()
            val pw1  : String = binding.password.text.toString()
            val pw2  : String = binding.passwordcheck.text.toString()
            val name : String = binding.name.text.toString()

            if(pw1.equals(pw2)) {
                Toast.makeText(this, "회원가입 완료! id : $id, pw : $pw1, name : $name", Toast.LENGTH_SHORT).show()
                startActivity(intent2)
            } else {
                Toast.makeText(this, "비밀번호 확인이 다릅니다.", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }

        binding.backmain.setOnClickListener {
            val intent : Intent = Intent(this@SixthPracticeRegister, SixthPractice::class.java)
            startActivity(intent)
        }
    }
}