package com.example.test13_16_17_18.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain424Binding

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/java/com/example/test13/MainActivity424.kt
// 뷰 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/res/layout/activity_main424.xml
class MainActivity424 : AppCompatActivity() {
    var count = 0
    lateinit var binding: ActivityMain424Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain424Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // CSR PSD는 항상 있음
        // savedInstanceState : 임시 파일 (메모리) 저장해서 가져오는 역할
        binding.plusCountButton.setOnClickListener {
            count++
            binding.countResultView.text="$count"
        }
    }

    // savedInstanceState : setter 개념
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data1 = savedInstanceState.getString("data1")
        val data2 = savedInstanceState.getInt("data2")

        binding.countResultView.text="$data1 - $data2"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("kkang","onSaveInstanceState..........")
        outState.putString("data1", "hello")
        outState.putInt("data2", 10)
    }
}