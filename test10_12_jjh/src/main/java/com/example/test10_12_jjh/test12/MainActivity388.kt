package com.example.test10_12_jjh.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.databinding.ActivityMain388Binding
import com.example.test10_12_jjh.fragment.OneFragment
import com.example.test10_12_jjh.fragment.ThreeFragment
import com.example.test10_12_jjh.fragment.TwoFragment
import com.google.android.material.tabs.TabLayoutMediator

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test12/src/main/java/com/example/test12/MainActivity388.kt
// 뷰 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test12/src/main/res/layout/activity_main388.xml
class MainActivity388 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain388Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 탭 레이아웃, 뷰 페이저 기본 연동 설정
        val tabLayout = binding.tabs
        val viewPager = binding.viewpager

        // 뷰페이저 - 어댑터 -> 프래그먼트 vs xml
        viewPager.adapter= MyFragmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab${(position + 1)}"
        }.attach()
    }

    // 공통 액티비티 코드를 리팩토링 할 때, 아래 매개변수 영역에 액티비티 타입으로 선언하는 방식
    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        // 클래스가 초기화(객체 생성) 후 실행되는 코드
        init {
            fragments= listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}