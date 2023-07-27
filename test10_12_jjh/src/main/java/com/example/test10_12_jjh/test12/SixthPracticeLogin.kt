package com.example.test10_12_jjh.test12

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test10_12_jjh.databinding.ActivitySixthPracticeLoginBinding

class SixthPracticeLogin : AppCompatActivity() {
    var db : DatabaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DatabaseHelper(this)
        login()
    }

    fun login() {
        val binding = ActivitySixthPracticeLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backmain.setOnClickListener {
            val intent = Intent(this@SixthPracticeLogin, SixthPractice::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            val id : String = binding.id.text.toString()
            val pw : String = binding.password.text.toString()
            val member = db!!.findMember(id, pw)
            if(member.count == 0) {
                Toast.makeText(this@SixthPracticeLogin, "등록된 회원이 아닙니다.", Toast.LENGTH_SHORT).show()
                val intent : Intent = Intent(this@SixthPracticeLogin, SixthPracticeLogin::class.java)
                startActivity(intent)
            } else {
                member.moveToNext()
                Toast.makeText(this@SixthPracticeLogin, "회원정보창으로 이동합니다", Toast.LENGTH_SHORT).show()
                val intent : Intent = Intent(this@SixthPracticeLogin, SixthPracticeMemInfo::class.java)
                val loginpref = getSharedPreferences("login_pref", MODE_PRIVATE)
                loginpref.edit().run {
                    putString("mid", member.getString(0))
                    putString("id", member.getString(1))
                    putString("pw", member.getString(2))
                    putString("name", member.getString(3))
                    putString("address", member.getString(4))
                    putString("phone", member.getString(5))
                    commit()
                }
                startActivity(intent)
            }
        }

        binding.registerBtn.setOnClickListener {
            val intent = Intent(this@SixthPracticeLogin, SixthPracticeRegister::class.java)
            startActivity(intent)
        }
    }
}