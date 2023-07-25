package com.example.test13_16_17_18.test13

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain406Binding

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/java/com/example/test13/MainActivity406.kt
class MainActivity406 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain406Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent: Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            // 후처리 함수 요청 코드 값 : 10, 디테일 액티비티에서 데이터를 가지고 오는 경우
            // 요청코드를 기준으로 비교함.
            startActivityForResult(intent, 10)
        }
    }

    // 후처리 즉, 디테일 화면 -> 원래화면으로 데이터를 가지고 올 경우, 자동 호출 함수.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == RESULT_OK) {
            val result = data?.getStringExtra("resultData")
            val binding = ActivityMain406Binding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.resultFromDetail.text = result
            Toast.makeText(this@MainActivity406, "후처리 결과값 확인 $result", Toast.LENGTH_SHORT).show()
        }
    }
}