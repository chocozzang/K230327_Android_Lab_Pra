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
        val tidetimes = tide?.result?.data
        var tempStr : String = ""
        if(tidetimes?.size!! == 0) {
            tempStr.plus("조석 정보 없음")
        }
        else {
            Log.d("apitest", "$position")
            tempStr += "날짜 : ${tidetimes?.get(0)?.tidetime?.substring(0, 11)}"
            var highcnt = 0
            var lowcnt  = 0
            for (i in 0 until tidetimes?.size!!) {
                if (tidetimes?.get(i)?.tidetype.equals("고조")) {
                    tempStr += "\n만조 시간 #${highcnt + 1} : ${tidetimes?.get(i)?.tidetime?.substring(11)} (${tidetimes?.get(i)?.tidelevel})"
                    highcnt++
                } else {
                    tempStr += "\n간조 시간 #${lowcnt + 1} : ${tidetimes?.get(i)?.tidetime?.substring(11)} (${tidetimes?.get(i)?.tidelevel})"
                    lowcnt++
                }
            }
        }
        Log.d("apitest", tempStr)
        binding.practiceItemDetail.text = tempStr
        binding.practiceRoot.setOnClickListener { 
            // 보통 상세페이지와의 연결
        }
    }

}