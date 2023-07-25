package com.example.test13_16_17_18.test13

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain414Binding

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/java/com/example/test13/MainActivity414.kt
// 뷰 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/res/layout/activity_main414.xml
class MainActivity414 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LifeCycle", "onCreate() 호출")
        val binding = ActivityMain414Binding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // ACTION_EDIT은 시스템에서 사용하지 않음
        binding.button1.setOnClickListener {
            val intent = Intent()
            intent.action = "ACTION_EDIT"
            intent.data = Uri.parse("http://www.google.co.kr")
            startActivity(intent)
        }
        
        // 액션 문자열은 시스템이 제공하는 Intent.ACTION"_VIEW
        // 웹 주소, 웹브라우저 연결, 위도, 경도 값이면 지도로 알아서 연결
        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7749,127.4194"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle", "onStart() 호출")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LifeCycle", "onRestart() 호출")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "onResume() 호출")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "onPause() 호출")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "onStop() 호출")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "onDestroy() 호출")
    }
}