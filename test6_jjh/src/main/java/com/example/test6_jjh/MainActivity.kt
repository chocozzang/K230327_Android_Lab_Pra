package com.example.test6_jjh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.test6_jjh.databinding.ActivityMainBinding

// 액티비티 : 화면을 그려주는 역할
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 상태를 나타내는 지역 변수를 선언함
        // 스위치를 위한 변수 -> 이미지를 보여주거나 안보여주는 경우

        var status : Int = 0

        // savedInstanceState : Bundle : 임시 객체 (= Session)
        super.onCreate(savedInstanceState) // 화면을 그려줌
        // 레이아웃 샘플로 리니어 사용 중, 이유? 제약 조건 설정이 간단함
        // 뷰만 선택해서 배치하면 기본 가로/새로 방향으로 배치가 간단해짐

        val binding = ActivityMainBinding.inflate(layoutInflater)
        // 뷰 바인딩 적용 1
        setContentView(binding.root)
        // 뷰 바인딩 적용 2

        //setContentView(R.layout.activity_main)

        // 버튼 클릭 -> 이미지 show / hide
        // 버튼에 이벤트 클릭 리스너 설정 후 -> 결과로 이미지의 속성 변경 (visibility)
        // binding 객체 안에 사용하는 뷰의 모든 객체가 담겨있음
        // 버튼을 하나로 토글처럼 쓰려면 상태 패턴을 위한 변수를 선언해야함
        binding.btn1.setOnClickListener {
            if(status == 0) {
                binding.img1.visibility = View.GONE
                status = 1
            } else {
                binding.img1.visibility = View.VISIBLE
                status = 0
            }
        }
        // on/off 버튼을 별도로 두는 경우
        binding.btn2.setOnClickListener {
            binding.img1.visibility = View.VISIBLE
        }
        // 회원가입 버튼 클릭 시 회원가입 페이지로 이동
        // 화면 전환 시 데이터를 가지고 이동함
        binding.joinBtn.setOnClickListener {
            val intent : Intent = Intent(this@MainActivity, JoinActivity::class.java)
            startActivity(intent)
        }
    }
}