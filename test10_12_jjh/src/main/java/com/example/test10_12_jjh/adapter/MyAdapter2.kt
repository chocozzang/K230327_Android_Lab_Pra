package com.example.test10_12_jjh.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.databinding.Item2342Binding

class MyViewHolder2(val binding: Item2342Binding): RecyclerView.ViewHolder(binding.root)

class MyAdapter2(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val TAG : String = "test10_12"

    override fun getItemCount(): Int{
        Log.d(TAG, "init datas size: ${datas.size}")
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder2(Item2342Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG,"onBindViewHolder : $position")
        val binding = (holder as MyViewHolder2).binding
        //뷰에 데이터 출력
        binding.itemData.text = datas[position]

        //뷰에 이벤트 추가
        binding.itemRoot.setOnClickListener{
            // 보통 상세 페이지로의 연결을 많이 함
            Log.d(TAG, "item root click : $position")
        }

        //notifyDataSetChanged()
    }
}