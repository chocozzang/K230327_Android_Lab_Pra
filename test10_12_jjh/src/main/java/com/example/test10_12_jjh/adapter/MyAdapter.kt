package com.example.test10_12_jjh.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.databinding.Item342Binding


// 샘플 코드 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test11/src/main/java/com/example/test11/MainActivity342.kt
// 보통은 어댑터를 따로 분리해서 만들고,
// 구성품은 뷰홀더 클래스를 같이 구성하는 편
// 목록의 아이템 역할을 하는 뷰를 직접 만들어야함
// xml 레이아웃 -> 개별로 res -> layout 직접 만들기
// item_342.xml 레이아웃 가져다가 쓰기
// 뷰 -> 자동으로 뷰 바인딩이 동작해서 Item342Binding 파일을 메모리에 생성

// 뷰 홀더 매개변수 - 목록의 구성요소(아이템)
// 리사이클러 뷰를 매개변수로 사용함
class MyViewHolder(val binding: Item342Binding): RecyclerView.ViewHolder(binding.root)

// 어댑터 매개변수 - 현재는 임시 데이터(액티비티에서 생성함)이지만,
// 원래는 네트워크 라이브러리를 사용하여 데이터를 가져올 예정임.
class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    
    val TAG : String = "test10_12"

    // 목록의 요소에 대한 개수를 자동으로 파악해서 출력해주는 역할을 함 (logcat)
    override fun getItemCount(): Int{
        Log.d(TAG, "init datas size: ${datas.size}")
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(Item342Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    
    // 객체와 데이터를 연결
    // 데이터가 변경될 때마다, 이 함수가 자동으로 호출됨.
    // 받아온 데이터를 해당 뷰에 연결해서 보여주는 역할을 함
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG,"onBindViewHolder : $position")
        val binding=(holder as MyViewHolder).binding
        //뷰에 데이터 출력
        binding.itemData.text= datas[position]

        //뷰에 이벤트 추가
        binding.itemRoot.setOnClickListener{
            // 보통 상세 페이지로의 연결을 많이 함
            Log.d(TAG, "item root click : $position")
        }

        // 목록에 데이터를 임의로 받아오는 부분이 아니라, 기존 데이터에 추가 및 수정 시에
        // 반영이 되지 않는 경우가 있음. 그럴 때 사용하는 함수.
        // notifyDataSetChanged()
    }
}