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
class APIAdapter(val context : Context, val datas : List<TideModel>?, val tempers : List<temper>?, val lunars : List<String>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            var highStr : String = ""
            var counta = 0
            var countb = 0
            var lowStr : String = ""
            tempStr += "\n물때 : ${lunars?.get(position)}"
            for (i in 0 until tidetimes?.size!!) {
                if (tidetimes?.get(i)?.tidetype.equals("고조")) {
                    if(counta > 0) highStr += " / "
                    highStr += "${tidetimes?.get(i)?.tidetime?.substring(11, 16)}"
                    counta++
                } else {
                    if(countb > 0) lowStr += " / "
                    lowStr += "${tidetimes?.get(i)?.tidetime?.substring(11, 16)}"
                    countb++
                }
            }
            tempStr += "\n만조시간 : "
            tempStr += highStr
            tempStr += "\n간조시간 : "
            tempStr += lowStr
            var now = false
            if(tempers?.size!! == 0) {
                tempStr += "\n날씨 정보 없음"
            } else {
                var upTemp : String = ""
                var downTemp : String = ""
                for(i in position * 2 until (position + 1) * 2) {
                    if(tempers?.get(i)?.type == "TMX") upTemp += "${tempers?.get(i)?.temp?.toFloat()} °C"
                    if(tempers?.get(i)?.type == "TMN") downTemp += "${tempers?.get(i)?.temp?.toFloat()} °C"
                    if(tempers?.get(i)?.type == "TMP") {
                        tempStr += "\n현재기온 : ${tempers?.get(i)?.temp?.toFloat()} °C"
                        now = true
                        break
                    }
                }
                if(!now) {
                    tempStr += "\n최저/최고 기온 : "
                    tempStr += downTemp
                    tempStr += " / "
                    tempStr += upTemp
                }
            }
        }
        binding.practiceItemDetail.text = tempStr
        Log.d("google22", "${tempers?.size}")
        Log.d("google22", tempStr)
        binding.practiceRoot.setOnClickListener {
            // 보통 상세페이지와의 연결
        }
    }

}