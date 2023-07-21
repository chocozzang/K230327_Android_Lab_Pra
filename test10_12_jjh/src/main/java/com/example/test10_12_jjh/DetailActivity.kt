package com.example.test10_12_jjh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test10_12_jjh.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 원본 소스의 내용 복사
    }
}