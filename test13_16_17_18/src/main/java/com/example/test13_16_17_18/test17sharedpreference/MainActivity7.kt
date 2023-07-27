package com.example.test13_16_17_18.test17sharedpreference

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test13_16_17_18.databinding.ActivityMain7Binding

// 코드 재사용 : test13의 MainActivity, DetailActivity
//
class MainActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼 클릭해서, 정방향, 메인 -> 디테일 화면으로 이동
        // this -> MainActivity
        binding.button1.setOnClickListener {
            // shared preference 작업
            // 여기서 데이터 set -> detail에서 데이터를 가져옴
            // 프리퍼런스에 데이터 저장
            // my_prefs라는 파일에 Map(xml) 형태로 값이 저장됨
            val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            // 공유파일처럼 해당 데이터를 저장함
            // 저장 위치 data/data/패키지/shared_prefs/my_prefs.xml
            sharedPref.edit().run {
                putString("data3", "hello")
                putInt("data4", 10)
                commit()
            }
            //::class.java 클래스 레퍼런스 타입으로의 전달은 보통, 내부 앱에서 전달할 때 사용함
            val intent: Intent = Intent(this, DetailActivity2::class.java)
            /// Map처럼 key와 value의 형태로 데이터를 보내고 가져오는 작업을 함
            //intent.putExtra("data1", "hello")
            //intent.putExtra("data2", 10)
            // 시스템에 설정한 인텐트를 전달함.
            startActivity(intent)

        }
    }
}