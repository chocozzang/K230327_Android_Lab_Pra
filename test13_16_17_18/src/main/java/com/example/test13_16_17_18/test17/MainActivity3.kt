package com.example.test13_16_17_18.test17

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain3Binding

// 1. 기본 SQLite
// 2. 연습문제 17
// 3. CRUD 블로그 샘플소스
// https://github.com/lsy3709/K230201AndroidLab/tree/master
// /test17-crud/src/main/java/com/example/test17_crud
// 4. 제트팩 라이브러리에서 구글에서 공식적으로 SQLite보다 Room 사용 권장
// 5. 샘플코드 소개

// menu_add
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test17-2/src/main/res/menu/menu_add.xml
// menu_main
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test17-2/src/main/res/menu/menu_main.xml
// 1) AddActivity.kt
// 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test17-2/src/main/java/com/example/test17_2/AddActivity.kt
// 2) DBHelper.kt -- 일반 코틀린 클래스
// 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test17-2/src/main/java/com/example/test17_2/DBHelper.kt
// 3) MainActivity.kt
// 코드 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test17-2/src/main/java/com/example/test17_2/MainActivity.kt
// 뷰 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test17-2/src/main/res/layout/activity_main.xml
// 4) MyAdapter.kt -- 일반 코틀린 클래스
// 코드 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test17-2/src/main/java/com/example/test17_2/MyAdapter.kt
// 액션바를 붙인 후에 작업
class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    // AddActivity에서 입력된 한 줄의 텍스트를 요소로 리스트에 저장함
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 후처리.
        // 테스트 입력 후 저장 버튼을 누르면 여기로 돌아옴
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            // 돌아온 결과값은 항상 it이라는 객체에 Map 형태로 저장함
            // 키 : result 라는 부분의 값을 가지고와서
            // datas 라는 String list에 저장함
            // 어댑터 객체의 함수 중에 변경 사항을 알리는 함수를 수행한 후
            // 리사이클러 뷰에 적용함
            it.data!!.getStringExtra("result")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }
        // 플로팅 액션 바 -> 클릭 이벤트 처리 -> 입력 액티비티로 이동
        // 하단에 플러스 버튼 클릭시 -> 화면이동 -> 투두 입력 창
        // 후처리를 하는 함수 -> requestLauncher
        // 입력창에서 todo를 입력한 후에 입력한 값을 가지고 되돌아옴
        // AddActivity에 setter 부분이 있음
        binding.mainFab.setOnClickListener {
            // 화면 전환
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }

        datas = mutableListOf<String>()

        // 조회
        // readableDatabase -> 데이터베이스 읽기
        val db = DBHelper(this).readableDatabase
        // 커서(cursor) 쉽제, 조회된 결과를 테이블 형식으로 저장한 객체
        val cursor = db.rawQuery("select * from TODO_TB", null)
        // 테이블 형식으로 저장되어 있음
        cursor.run {
            // 반복문으로 커서 테이블에 데이터를 한 행씩 불러와서 해당 컬럼을 가져옴
            // cf. 커서는 인덱스가 1부터 시작함 (1행)
            while(moveToNext()){
                datas?.add(cursor.getString(1))
            }
        }
        // DB 서버에서 조회된 내용을 현재 메모리의 datas 리스트에 다 담음
        // DB 사용 반납
        db.close()

        // 리사이클러뷰 적용 부분
        val layoutManager = LinearLayoutManager(this)
        // 디비 서버에서 받은 데이터를 메모리 상의 임시 객체 datas에 담아서
        // 어댑터 클래스에 연결 하는 부분
        binding.mainRecyclerView.layoutManager = layoutManager
        adapter = MyAdapter(datas)
        // 어댑터 클래스에 적용된 데이터 <-> 뷰 결과를 뷰에 적용
        binding.mainRecyclerView.adapter=adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId===R.id.menu_main_setting){
//            val intent = Intent(this, SettingActivity::class.java)
//            startActivity(intent)
//        }
//        return super.onOptionsItemSelected(item)
//    }
}