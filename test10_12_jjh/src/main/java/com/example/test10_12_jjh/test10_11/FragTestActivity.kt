package com.example.test10_12_jjh.test10_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.databinding.ActivityFragTestBinding
import com.example.test10_12_jjh.fragment.TwoFragment

// 프래그먼트 출력을 위한 테스트 코드
class FragTestActivity : AppCompatActivity() {
    // 현재 FragTestActivity에 프래그먼트가 출력될 예정.
    // 프래그먼트 -> 마치 액티비티와 유사하게 동작함
    // ex) 생명주기, 뷰 바인딩 등.
    lateinit var binding : ActivityFragTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 프래그먼트 기본 테스트
        // 구성 방법 1 : xml로 구현
        // 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/res/layout/activity_main337.xml
        // 출력 방식이, 액티비티에 name으로 해당 프래그먼트를 지정해서 출력

        // 구성 방법 2 : 코드로 구현
        // 액티비티 코드에서 해당 프래그먼트를 호출 출력하는 방법.
        // 프래그먼트 또 만들기
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = TwoFragment()
        transaction.add(R.id.fragment_content, fragment)
        // 추후에 replace로 프래그먼트롤 교체해서 사용하면 됨.
        // 한 액티비티에 여러 프래그먼트를 할당할 수 있게 됨.

        // 백스택 설정시 커밋 이전 함수에서 설정
        // 뒤로가기 버튼을 클릭 시에 해당 액티비티를 종료하지 않고,
        // 메모리 사엥 있는 프래그먼트를 재사용 할 수 있도록 함
        // 옵션 설정이 없으면, 프래그먼트는 소멸되고 이후에 재생성 되어 시작함
        // 따라서 자원 소모가 발생할 수 밖에 없음
        transaction.addToBackStack(null)
        transaction.commit()
    }
}