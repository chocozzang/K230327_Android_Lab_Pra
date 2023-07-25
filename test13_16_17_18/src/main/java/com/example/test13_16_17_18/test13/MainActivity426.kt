package com.example.test13_16_17_18.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain426Binding

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/java/com/example/test13/MainActivity426.kt
// 뷰 경로 :
//
class MainActivity426 : AppCompatActivity() {
    lateinit var binding: ActivityMain426Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain426Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.showInputButton.setOnClickListener {
            binding.editView.requestFocus()
            manager.showSoftInput(binding.editView, InputMethodManager.SHOW_IMPLICIT)
        }
        binding.hideInputButton.setOnClickListener {
            manager.hideSoftInputFromWindow(currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}