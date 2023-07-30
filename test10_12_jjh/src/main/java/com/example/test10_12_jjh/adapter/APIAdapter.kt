package com.example.test10_12_jjh.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.databinding.ItemPracticeBinding
import com.example.test10_12_jjh.model.TideModel

// 커스텀 뷰홀더
// 아이템에 대한 뷰를 바인딩함
class APIViewHolder(val binding : ItemPracticeBinding) : RecyclerView.ViewHolder(binding.root)

// 커스텀 어댑터
// onCreateViewHolder
// getItemCount
// onBindViewHolder를 Implement할 것
class APIAdapter(val context : Context, val datas : List<TideModel>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder 
        = APIViewHolder(ItemPracticeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int {
        return datas?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("apitest", "뷰바인더홀더 ::: $position")
        val binding = (holder as APIViewHolder).binding
        val tide = datas?.get(position)
        binding.practiceItem.text = "측정위치 : "+ tide?.result?.meta?.postid + " - " + tide?.result?.meta?.postname
        binding.practiceItemDetail.text = """
            날짜 : ${tide?.result?.data?.get(0)?.tidetime?.substring(0, 11)}
            만조 시간 1 : ${tide?.result?.data?.get(0)?.tidetime?.substring(11)}
            간조 시간 1 : ${tide?.result?.data?.get(1)?.tidetime?.substring(11)}
            만조 시간 2 : ${tide?.result?.data?.get(2)?.tidetime?.substring(11)}
            간조 시간 2 : ${tide?.result?.data?.get(3)?.tidetime?.substring(11)}           
        """.trimIndent()
        binding.practiceRoot.setOnClickListener { 
            // 보통 상세페이지와의 연결
        }
    }

}