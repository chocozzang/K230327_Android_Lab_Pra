package com.example.test13_16_17_18.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13_16_17_18.databinding.ActivityMainBinding

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/java/com/example/test13/MainActivity.kt
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼 클릭해서, 정방향, 메인 -> 디테일 화면으로 이동
        // this -> MainActivity
        binding.button1.setOnClickListener {
            // ::class.java 클래스 레퍼런스 타입으로의 전달은 보통, 내부 앱에서 전달할 때 사용함
            val intent: Intent = Intent(this, DetailActivity::class.java)
            // Map처럼 key와 value의 형태로 데이터를 보내고 가져오는 작업을 함
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            // 시스템에 설정한 인텐트를 전달함.
            startActivity(intent)
        }
    }
}