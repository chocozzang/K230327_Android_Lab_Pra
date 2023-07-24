package com.example.test10_12_jjh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test10_12_jjh.databinding.ActivityViewPager2FragmentBinding
import com.example.test10_12_jjh.databinding.ActivityViewPager2RecycleBinding
import com.example.test10_12_jjh.fragment.OneFragment
import com.example.test10_12_jjh.fragment.ThreeFragment
import com.example.test10_12_jjh.fragment.TwoFragment

class ViewPager2_Fragment_Activity : AppCompatActivity() {
    lateinit var binding : ActivityViewPager2FragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2FragmentBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_view_pager2_fragment)
        setContentView(binding.root)

        // 뷰페이저2 구현 - 프래그먼트 방식으로 연결
        // 프래그먼트 구현 방법1) xml 태그 출력
        // 프래그먼트 구현 방법2) 액티비티 코드 상에서 출력
        // 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/res/layout/activity_main355.xml
        // layout 부분 뷰 참고
        // 코드 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/java/com/example/test11/MainActivity355.kt
        val datas = mutableListOf<String>()
        for(i in 1..3){
            datas.add("Item $i")
        }
        val adapter= MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
    }

    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            fragments= listOf(OneFragment(), TwoFragment(), ThreeFragment())
            Log.d("kkang" ,"fragments size : ${fragments.size}")
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}