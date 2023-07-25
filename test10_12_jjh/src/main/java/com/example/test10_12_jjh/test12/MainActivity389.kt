package com.example.test10_12_jjh.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.databinding.ActivityMain389Binding

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test12/src/main/java/com/example/test12/MainActivity389.kt
// 뷰 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test12/src/main/res/layout/activity_main389.xml
class MainActivity389 : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain389Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.mainDrawerView.setNavigationItemSelectedListener {
            Log.d("kkang","navigation item click... ${it.title}")
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}