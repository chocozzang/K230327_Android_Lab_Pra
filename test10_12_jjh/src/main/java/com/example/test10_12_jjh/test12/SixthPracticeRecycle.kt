package com.example.test10_12_jjh.test12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10_12_jjh.adapter.AdapterPractice
import com.example.test10_12_jjh.databinding.ActivitySixthPracticeRecycleBinding

class SixthPracticeRecycle : AppCompatActivity() {
    lateinit var binding : ActivitySixthPracticeRecycleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixthPracticeRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tempdatas = mutableListOf<String>()
        for(i in 1..20) {
            tempdatas.add("Temp Item $i")
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.myrecyclerView.layoutManager = layoutManager
        binding.myrecyclerView.adapter = AdapterPractice(tempdatas)
        binding.myrecyclerView.addItemDecoration(
            DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL)
        )
    }
}