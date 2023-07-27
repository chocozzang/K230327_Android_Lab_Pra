package com.example.test13_16_17_18.test17

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityAddBinding

// 코드 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test17-2/src/main/java/com/example/test17_2/AddActivity.kt
// 뷰 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test17-2/src/main/res/layout/activity_add.xml
// menu_add 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test17-2/src/main/res/menu/menu_add.xml
class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 액션바의 메노 아이템의 클릭 리스너 부분에 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.menu_add_save ->{
            //add........................
            // 입력창에서 입력된 값을 가져옴
            // DBHelper 클래스 (일반 클래스), 매개변수로 현재 액티비티인 this를 사용하고
            // writableDatabase 문자열을 이용해서 insert 작업을 함
            val inputData = binding.addEditView.text.toString()
            val db = DBHelper(this).writableDatabase
            // 기본 함수 중에 execSQL 함수를 사용함
            // 첫번째 매개변수는  SQL 문법인 INSERT 사용
            // 두번째 매개변수는 ? 동적인 매개변수로 받음 -> 할당될 값을 배열의 요소로 제공
            db.execSQL(
                "insert into TODO_TB (todo) values (?)",
                arrayOf<String>(inputData)
            )
            // DB 사용 후에는 항상 close를 해야함
            db.close()
            // 후처리 부분
            // 메인 플로팅 액션 버튼 클릭 -> 현재 add 입력창에서 텍스트를 입력한 후
            // 입력된 데이터를 인텐트로 다시 돌려보냄
            val intent = intent
            intent.putExtra("result", inputData)
            setResult(Activity.RESULT_OK, intent)
            finish()
            // 응답 메세지를 보낸 후 종료함
            true
        }
        else -> true
    }
}