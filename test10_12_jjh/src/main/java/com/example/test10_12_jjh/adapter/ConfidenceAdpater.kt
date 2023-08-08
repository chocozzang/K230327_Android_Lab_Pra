package com.example.test10_12_jjh.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.databinding.PercentageRecyclerBinding

// 커스텀 뷰홀더
// 아이템에 대한 뷰를 바인딩함
class ConfidenceViewHolder(val binding : PercentageRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

// 커스텀 어댑터
// onCreateViewHolder
// getItemCount
// onBindViewHolder를 Implement할 것
class ConfidenceAdpater(val confidences : List<String>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("test16", "mytesttest11232362")
        return ConfidenceViewHolder(PercentageRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount(): Int {
        return confidences?.size!!
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ConfidenceViewHolder).binding
        Log.d("test16", "66${confidences?.get(position)}66")
        binding.confpercent.text = confidences?.get(position)
    }

}