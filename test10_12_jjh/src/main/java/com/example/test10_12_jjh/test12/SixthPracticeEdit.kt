package com.example.test10_12_jjh.test12

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test10_12_jjh.databinding.ActivitySixthPracticeEditBinding

class SixthPracticeEdit : AppCompatActivity() {
    lateinit var binding : ActivitySixthPracticeEditBinding
    var db : DatabaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixthPracticeEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DatabaseHelper(this)

        val meminfo = getSharedPreferences("login_pref", MODE_PRIVATE)
        val mid = meminfo.getString("mid", null)
        val id = meminfo.getString("id", null)
        val pw = meminfo.getString("pw", null)
        val name = meminfo.getString("name", null)
        val address = meminfo.getString("address", null)
        val phone = meminfo.getString("phone", null)

        binding.id.hint = id
        binding.name.hint = name
        binding.address.hint = address
        binding.phone.hint = phone

        binding.editBtn.setOnClickListener {
            val edid = binding.id!!.text.toString()
            val edpw = binding.id!!.text.toString()
            val ednm = binding.id!!.text.toString()
            val edad = binding.id!!.text.toString()
            val edph = binding.id!!.text.toString()
            if(edid != "" && edpw != "" && ednm != "" && edad != "" && edph != "") {
                val isUpdated = db!!.updateMember(
                    mid.toString(),
                    binding.id!!.text.toString(),
                    binding.password!!.text.toString(),
                    binding.name!!.text.toString(),
                    binding.address!!.text.toString(),
                    binding.phone!!.text.toString()
                )
                if (isUpdated == true) {
                    Toast.makeText(this@SixthPracticeEdit, "회원 정보 수정 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SixthPracticeEdit, SixthPractice::class.java)
                    val meminfo = getSharedPreferences("login_pref", MODE_PRIVATE)
                    meminfo.edit().clear()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SixthPracticeEdit, "회원 정보 수정 실패", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@SixthPracticeEdit, "회원 정보 수정 실패", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backmain.setOnClickListener {
            val intent = Intent(this@SixthPracticeEdit, SixthPractice::class.java)
            startActivity(intent)
        }
    }
}