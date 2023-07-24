package com.example.test10_12_jjh.test10_11

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.databinding.ActivityViewPager2RecycleBinding
import com.example.test10_12_jjh.databinding.Item354Binding

class ViewPager2_Recycle_Activity : AppCompatActivity() {

    lateinit var binding : ActivityViewPager2RecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2_recycle)

        // 샘플코드 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/java/com/example/test11/MainActivity354.kt
        binding = ActivityViewPager2RecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<String>()
        for(i in 1..3){
            datas.add("Item $i")
        }

        // 뷰 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/res/layout/item_354.xml
        binding.viewpager.adapter = MyPagerAdapter(datas)
    }
    
    // 뷰홀더, 어댑터 모두 필요함
    // res/layout/item_354.xml을 복사함
    class MyPagerViewHolder(val binding: Item354Binding): RecyclerView.ViewHolder(binding.root)

    class MyPagerAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun getItemCount(): Int{
            return datas.size
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyPagerViewHolder(Item354Binding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding=(holder as MyPagerViewHolder).binding
            //뷰에 데이터 출력
            binding.itemPagerTextView.text = datas[position]
            when(position % 3){
                0 -> binding.itemPagerTextView.setBackgroundColor(Color.RED)
                1 -> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
                2 -> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
            }
        }
    }
}