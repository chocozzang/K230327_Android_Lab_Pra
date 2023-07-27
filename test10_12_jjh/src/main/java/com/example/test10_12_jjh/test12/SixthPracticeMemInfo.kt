package com.example.test10_12_jjh.test12

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test10_12_jjh.databinding.ActivitySixthPracticeMemInfoBinding

class SixthPracticeMemInfo : AppCompatActivity() {
    lateinit var binding : ActivitySixthPracticeMemInfoBinding
    var db : DatabaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixthPracticeMemInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DatabaseHelper(this)

        val meminfo = getSharedPreferences("login_pref", MODE_PRIVATE)
        val mid = meminfo.getString("mid", null)
        val id = meminfo.getString("id", null)
        val pw = meminfo.getString("pw", null)
        val name = meminfo.getString("name", null)
        val address = meminfo.getString("address", null)
        val phone = meminfo.getString("phone", null)

        if(id == null || pw == null) {
            binding.memberinfo.text = "회원 정보가 없습니다"
        } else {
            binding.memberinfo.text = """
                ID : $id
                name : $name
                address : $address
                phone : $phone
            """.trimIndent()
        }

        binding.logoutBtn.setOnClickListener {
            meminfo.edit().clear()
            val intent = Intent(this@SixthPracticeMemInfo, SixthPracticeLogin::class.java)
            startActivity(intent)
        }

        binding.deleteBtn.setOnClickListener {
            val deleteRows = db!!.deleteMember(mid!!)
            if (deleteRows > 0) {
                Toast.makeText(this@SixthPracticeMemInfo, "데이터 삭제 성공", Toast.LENGTH_LONG).show()
                val intent = Intent(this@SixthPracticeMemInfo, SixthPractice::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this@SixthPracticeMemInfo, "데이터 삭제 실패", Toast.LENGTH_LONG).show()
            }
        }

        binding.editBtn.setOnClickListener {
            val intent = Intent(this@SixthPracticeMemInfo, SixthPracticeEdit::class.java)
            startActivity(intent)
        }

        binding.backBtn.setOnClickListener {
            val intent = Intent(this@SixthPracticeMemInfo, SixthPractice::class.java)
            startActivity(intent)
        }
    }
}