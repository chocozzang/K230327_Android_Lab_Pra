package com.example.test10_12_jjh.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.databinding.ItemPracticeBinding

// 커스텀 뷰홀더
// 아이템에 대한 뷰를 바인딩함
class ViewHolderPractice(val binding : ItemPracticeBinding) : RecyclerView.ViewHolder(binding.root)

// 커스텀 어댑터
// onCreateViewHolder
// getItemCount
// onBindViewHolder를 Implement할 것
class AdapterPractice(val datas : MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder 
        = ViewHolderPractice(ItemPracticeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ViewHolderPractice).binding
        binding.practiceItem.text = datas[position]
        binding.practiceRoot.setOnClickListener { 
            // 보통 상세페이지와의 연결
        }
    }

}