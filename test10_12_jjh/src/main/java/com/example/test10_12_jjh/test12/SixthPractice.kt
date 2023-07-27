package com.example.test10_12_jjh.test12

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.test10_12_jjh.databinding.ActivitySixthPracticeBinding

class SixthPractice : AppCompatActivity() {
    var myDB: DatabaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myDB = DatabaseHelper(this)
        viewAll()
    }

    fun viewAll() {
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

        binding.back.setOnClickListener {
            val intent = Intent(this@SixthPractice, PracticeActivity::class.java)
            startActivity(intent)
        }
        binding.memberlist!!.setOnClickListener(View.OnClickListener {
            // res에 조회된 , 테이블의 내용이 들어가 있다. select 의 조회의 결괏값있다.
            val res = myDB!!.allData
            // 결과가 없을 때
            if (res.count == 0) {
                ShowMessage("실패", "데이터를 찾을 수 없습니다.")
                return@OnClickListener
            }
            //결과가 있다면.
            // 자바에서, String 단점, 새로운 문자열이 있다면, 매번 새로 주소를 생성.
            // StringBuffer 하나의 객체에 해당 문자열을 추가만 하는 형태라서, 주소를 새로 생성안함.

            val buffer = StringBuffer()
            //res 형 ->Cursor , 쉽게 엑셀 마치 테이블 , 0행부터 시작한다.
            // res.moveToNext() -> 1행을 의미.
            while (res.moveToNext()) {
                buffer.append(
                    //코틀린 3중 따옴표, 멀티 라인.
                    // 1행의 첫번째 컬럼을 가져오기.
                    """
    MID: ${res.getString(0)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    ID: ${res.getString(1)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    NAME: ${res.getString(3)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    ADDRESS: ${res.getString(4)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    PHONE : ${res.getString(5)}
    
    """.trimIndent())}
            ShowMessage("데이터", buffer.toString())
        })
    }

    fun ShowMessage(title: String?, Message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }
}