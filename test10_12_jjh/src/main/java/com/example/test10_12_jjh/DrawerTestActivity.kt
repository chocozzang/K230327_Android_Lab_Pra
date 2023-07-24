package com.example.test10_12_jjh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.test10_12_jjh.databinding.ActivityDrawerTestBinding

class DrawerTestActivity : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var binding : ActivityDrawerTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_drawer_test)
        // 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/java/com/example/test11/MainActivity357.kt
        binding = ActivityDrawerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 뷰 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/res/layout/activity_main357.xml
        // 드로워 태그 하위에 뷰 2개가 있음
        // 1번째 뷰) 메인 화면 뷰
        // 2번째 뷰) 드로워 뷰 (사이드에서 메뉴 나오는 부분)
        //ActionBarDrawerToggle 버튼 적용
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        // 액션바 토글부분을 연결해서 버튼을 클릭하면 드로워화면이 보임
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}