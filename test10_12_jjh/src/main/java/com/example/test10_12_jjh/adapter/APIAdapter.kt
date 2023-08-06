package com.example.test10_12_jjh.adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.databinding.ItemPracticeBinding
import com.example.test10_12_jjh.model.TideModel
import com.example.test10_12_jjh.model.temper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// 커스텀 뷰홀더
// 아이템에 대한 뷰를 바인딩함
class APIViewHolder(val binding : ItemPracticeBinding) : RecyclerView.ViewHolder(binding.root)

// 커스텀 어댑터
// onCreateViewHolder
// getItemCount
// onBindViewHolder를 Implement할 것
class APIAdapter(val context : Context, val datas : List<TideModel>?, val tempers : List<temper>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder 
        = APIViewHolder(ItemPracticeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int {
        return datas?.size!!
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("apitest", "뷰바인더홀더 ::: $position")
        val binding = (holder as APIViewHolder).binding
        val tide = datas?.get(position)
        val tidetimes = tide?.result?.data
        val today = LocalDateTime.now()
        val targetDay = today.plusDays(position.toLong()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        var tempStr : String = ""
        if(tidetimes?.size!! == 0) {
            tempStr.plus("조석 정보 없음")
        }
        else {
            Log.d("apitest", "$position")
            tempStr += "날짜 : $targetDay"
            var highcnt = 0
            var lowcnt  = 0
            for (i in 0 until tidetimes?.size!!) {
                if (tidetimes?.get(i)?.tidetype.equals("고조")) {
                    tempStr += "\n만조 : ${tidetimes?.get(i)?.tidetime?.substring(11)} (${tidetimes?.get(i)?.tidelevel})"
                    highcnt++
                } else {
                    tempStr += "\n간조 : ${tidetimes?.get(i)?.tidetime?.substring(11)} (${tidetimes?.get(i)?.tidelevel})"
                    lowcnt++
                }
            }
            if(tempers?.size!! == 0) {
                tempStr += "\n날씨 정보 없음"
            } else {
                var upTemp : String = ""
                var downTemp : String = ""
                for(i in position * 2 until (position + 1) * 2) {
                    if(tempers?.get(i)?.type == "TMX") upTemp += "\n최고 기온 : ${tempers?.get(i)?.temp}"
                    if(tempers?.get(i)?.type == "TMN") downTemp += "\n최저 기온 : ${tempers?.get(i)?.temp}"
                    if(tempers?.get(i)?.type == "TMP") {
                        tempStr += "\n현재 기온 : ${tempers?.get(i)?.temp}"
                        break
                    }
                }
                tempStr += upTemp
                tempStr += downTemp
            }
        }
        Log.d("apitest", "${tempers?.size}")
        Log.d("apitest", tempStr)
        binding.practiceItemDetail.text = tempStr
        binding.practiceRoot.setOnClickListener { 
            // 보통 상세페이지와의 연결
        }
    }

}