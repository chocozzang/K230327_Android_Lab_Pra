package com.example.test10_12_jjh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10_12_jjh.adapter.MyAdapter
import com.example.test10_12_jjh.databinding.ActivityRecycleBinding

// 리사이클러 뷰를 출력해주는 빈 도화지
class RecycleActivity : AppCompatActivity() {
    lateinit var binding : ActivityRecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_recycle)
        binding = ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/java/com/example/test11/MainActivity342.kt
        // 순서 - 구성요소        
        // 1) 뷰 홀더 생성 - MyViewHolder
        // 2) 어댑터 설정  - MyAdapter
        // ** 뷰와 어댑터를 분리함 **
        // 3) 설정한 리사이클러 뷰를 메인(RecycleActivity)에 적용
        // 4) 임시 데이터를 사용할 것임(리스트), 보통 공공 데이터 등 디비에서 받아온 데이터를 사용

        // 임시 데이터
        val datas = mutableListOf<String>()
        for(i in 1..9){
            datas.add("Item $i")
        }

        // 레이아웃을 적용하는 코드
        // xml에 출력할 recyclerView를 설정하면 됨

        // 옵션에서 출력 모양을 정하는 부분
        // 보통 리니어 세로 / 가로 방향으로 출력하고,
        // 표 형식, 지그재그로 표현 옵션이 있음
        // 구현 옵션만 변형하여 확인
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // 만들어둔 리사이클러 뷰를 현재 액티비티에 적용

        // 기존 코드
        // binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // 변경 코드
        binding.recyclerView.layoutManager = layoutManager

        // 리사이클러 뷰의 어댑터를 커스텀한 어댑터와 연결
        // 뷰의 데이터를 변경함
        binding.recyclerView.adapter = MyAdapter(datas)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL)
        )

    }
}