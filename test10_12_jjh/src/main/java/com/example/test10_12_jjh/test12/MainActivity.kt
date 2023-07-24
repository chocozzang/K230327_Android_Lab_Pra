package com.example.test10_12_jjh.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test10_12_jjh.R

// test11 -> MainActivity가 사용중이므로
// test12 -> MainActivity는 activity_main2.xml로 설정되었음
// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test12/src/main/java/com/example/test12/MainActivity.kt
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}