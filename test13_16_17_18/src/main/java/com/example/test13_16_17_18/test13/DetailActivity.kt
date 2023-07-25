package com.example.test13_16_17_18.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("kkang","DetailActivity..onCreate..........")
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 인텐트에서 전달받은 값을 가져오는 역할을 함 == getter
        // 가져오들은 메서드을은 타입이 각각 있음. 지금은, 문자열 정수 위주로 연습하면 될 것임
        // 실수값은 주로 쓰지는 않음 -- 모바일 기준에서는
        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getIntExtra("data2", 0)

        // 후처리는 메인->디테일 이후에 메인<-디테일 진행방향이 반대임
        // 다른 액티비티에서 확인할 수 있으며, 후처리를 시작하는 함수는 따로있음.
        binding.detailResultView.text = "data1: $data1, data2: $data2"

        binding.detailButton.setOnClickListener {
            // 2번째 디테일 -> 1번째 메인으로 보냄
            intent.putExtra("resultData","world")
            setResult(RESULT_OK, intent)
            // 현재 액티비티를 종료
            // 카카오톡 프로필을 변경할 때, 갤러리에서 사진은 한 번 설정한 후,
            // 갤러리는 종료되어도 상관없음.
            finish()
        }



    }
}