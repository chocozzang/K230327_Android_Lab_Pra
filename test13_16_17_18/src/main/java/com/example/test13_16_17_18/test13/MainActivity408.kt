package com.example.test13_16_17_18.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain408Binding

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/java/com/example/test13/MainActivity408.kt
// 뷰 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/res/layout/activity_main408.xml
class MainActivity408 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain408Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 후처리 결과를 받아오는 함수
        // 현재는 이 함수를 사용할 것을 권장하고 있음.
        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            // 시스템에서 제공해주고 있는 부분임
            // ActivityResultContracts.StartActivityForResult()
            // pdf 기준 11/56페이지
            // ex. StartActivityForResult() 후처리.
            // ex. TakePicture : 사진 촬영, 저장, 비트맵 획득.
            // ex. RequestPermssion : 권한 요청, 허락 여부 파악.
            ActivityResultContracts.StartActivityForResult())
        {
            // it, 후처리의 결과 객체.
            val resultData = it.data?.getStringExtra("resultData")
            binding.mainResultView.text = "result : $resultData"
        }

        binding.button1.setOnClickListener {

            val intent: Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            // 후처리 함수 호출) 현재 메인 -> 디테일
            // 2번 디테일 화면에서, 데이터를 잘 설정해서 되돌려주면,
            // 위에 정의된 ActivityResultContracts.registerForActivityResult()로 이동함
            // 콜백 함수의 결과를 처리창에서 작업함.
            requestLauncher.launch(intent)
        }
    }
}