package com.example.test10_12_jjh.test12

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.databinding.ActivityMain378Binding
import com.example.test10_12_jjh.databinding.ItemRecyclerviewBinding

// 소스 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test12/src/main/java/com/example/test12/MainActivity378.kt
// 뷰 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test12/src/main/res/layout/activity_main378.xml
// 컴파일러 에러 부분
// 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test12/src/main/res/layout/item_recyclerview.xml
// 리사이클러뷰에서 아이템 구성 요소, 메뉴 아이템 뷰 부분이 없어서 1차 문법 체크가 필요함
class MainActivity378 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain378Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val datas = mutableListOf<String>()
        for(i in 1..20){
            datas.add("Item $i")
        }

        // ****** 리사이클러 뷰 설정 ******* 매우 중요함
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager
        val adapter = MyAdapter(datas)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(MyDecoration(this))
    }

    // 메뉴 경로 :
    // https://github.com/lsy3709/AndroidLab/blob/master
    // /test12/src/main/res/menu/menu_378.xml
    // ItemRecyclerviewBinding이 없음
    // https://github.com/lsy3709/AndroidLab/blob/master
    // /test12/src/main/res/layout/item_recyclerview.xml
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_378, menu)
        return super.onCreateOptionsMenu(menu)
    }

    class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        override fun getItemCount(): Int{
            return datas.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding=(holder as MyViewHolder).binding
            binding.itemData.text= datas[position]
        }
    }

    class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val index = parent.getChildAdapterPosition(view) + 1

            if (index % 3 == 0) //left, top, right, bottom
                outRect.set(10, 10, 10, 60)
            else
                outRect.set(10, 10, 10, 0)

            view.setBackgroundColor(Color.parseColor("#28A0FF"))
            ViewCompat.setElevation(view, 20.0f)

        }
    }
}