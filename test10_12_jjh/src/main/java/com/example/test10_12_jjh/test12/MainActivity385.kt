package com.example.test10_12_jjh.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.databinding.ActivityMain385Binding
import com.example.test10_12_jjh.fragment.OneFragment
import com.example.test10_12_jjh.fragment.ThreeFragment
import com.example.test10_12_jjh.fragment.TwoFragment
import com.google.android.material.tabs.TabLayout

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test12/src/main/java/com/example/test12/MainActivity385.kt
// 뷰 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test12/src/main/res/layout/activity_main385.xml
class MainActivity385 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain385Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 탭 레이아웃 기본 설정
        val tabLayout = binding.tabs

        // 탭 레이아웃과 프래그먼트 연동
        // 시작 시의 프레임 레이아웃을 설정함 -> 먼저 OneFragment를 출력하겠다는 이야기임
        supportFragmentManager.beginTransaction().add(R.id.tabContent, OneFragment()).commit()
        // 탭 선택 시 이벤트 핸들러 설정 부분 - NO NAME CLASS
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            // 탭 버튼 선택시 이벤트
            // 탭을 매개변수로 두었음
            // 탭 클릭시에 해당 문자열을 기준으로 조건문이 동작함
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // transaction을 선언함 -> 프래그먼트를 쓰겠다는 의미
                val transaction = supportFragmentManager.beginTransaction()
                when(tab?.text){
                    // 처음 영역을 다른 영역으로 교체하겠다는 것을 의미함
                    "Tab1"-> transaction.replace(R.id.tabContent, OneFragment())
                    "Tab2"-> transaction.replace(R.id.tabContent, TwoFragment())
                    "Tab3"-> transaction.replace(R.id.tabContent, ThreeFragment())
                }
                transaction.commit()
            }
            //선택된 탭 버튼을 다시 선택하는 경우
            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("kkang", "onTabReselected........")
            }
            //선택된 탭버튼이 다른 탭 버튼을 눌러 선택 해제 되는 경우
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("kkang", "onTabUnselected........")
            }
        })
    }


}