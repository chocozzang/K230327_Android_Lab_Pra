package com.example.firebasetest20

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasetest20.databinding.ActivityMainBinding
import com.example.firebasetest20.model.ItemData
import com.example.firebasetest20.recycler.MyAdapter
import com.example.firebasetest20.util.myCheckPermission

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이미지 접근 여부 - 미디어
        // 매 액티비티에서 사용할 수 있도록 파일을 분리함
        myCheckPermission(this)
        // 플로팅 액션 버튼 클릭 이벤트 리스너
        binding.addFab.setOnClickListener {
            // 유효성 체크 - 인증 여부 확인
            if(MyApplication.checkAuth()){
                // 인증이 되어있는 경우 AddActivity로 이동함
                startActivity(Intent(this, AddActivity::class.java))
            }else {
                // 인증되지 않으면 이동하지 않음
                Toast.makeText(this, "인증진행해주세요..",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        // 인증된 경우
        if(!MyApplication.checkAuth()){
            binding.logoutTextView.visibility = View.VISIBLE
            binding.mainRecyclerView.visibility = View.GONE
        }
        // 아닌 경우
        else {
            binding.logoutTextView.visibility = View.GONE
            binding.mainRecyclerView.visibility = View.VISIBLE
            makeRecyclerView()
        }
    }

    // 이벤트 리스너 액션바 메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, AuthActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    // 리사이클러 뷰를 출력하는 함수를 만들어서 사용중임
    private fun makeRecyclerView(){
        // 파이어 스토어의 컬렉션 객체를 선택하는 함수
        MyApplication.db.collection("news")
            .get()
            // 성공시
            // news라는 컬렉션(테이블)에서 모든 문자를 가져옴
            .addOnSuccessListener {result ->
                // 임시로 저장할 빈 리스트를 만듦
                // DTO(=VO), ItemData
                val itemList = mutableListOf<ItemData>()
                // 반복문으로 받아온 문서를 하나씩 꺼내서 작업함
                for(document in result){
                    // document.toObject -> json, jackson 등 해당 모델 클래스에 자동으로 매핑함
                    // 받아온 데이터를 지정한 클래스 형으로 자동 변환 (매핑)
                    val item = document.toObject(ItemData::class.java)
                    // 문서 고유 아이디를 할당
                    item.docId=document.id
                    // 각 ItemData 형으로 리스트에 담음
                    itemList.add(item)
                }
                // 리사이클러 뷰에 데이터를 집어넣어서 출력함
                // adapter를 호출함.. -> 파이어베이스에서 받아온 데이터와 연결
                binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
                binding.mainRecyclerView.adapter = MyAdapter(this, itemList)
            }
            // 실패시
            .addOnFailureListener{exception ->
                Log.d("kkang", "error.. getting document..", exception)
                Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }
    }
}