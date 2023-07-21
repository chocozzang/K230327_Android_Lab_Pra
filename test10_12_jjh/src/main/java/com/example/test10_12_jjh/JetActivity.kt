package com.example.test10_12_jjh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.test10_12_jjh.databinding.ActivityJetBinding

class JetActivity : AppCompatActivity() {
    lateinit var binding : ActivityJetBinding
    val TAG : String = "test10_12"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 직접 만들어서 뷰에 작성
        // 뷰, 뒷단 코드 가져오기
        // 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/java/com/example/test11/MainActivity330.kt
        // 뷰 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/res/menu/menu_330.xml
        setSupportActionBar(binding.toolbar)

    }
    
    // 메뉴, 액션바 기초 (테마 -> 툴바 대체)
    // 구성 방법 1) 코드 2) xml 구성 (현재는 xml로 작업중)
    // 코드 경로 :
    // https://github.com/lsy3709/AndroidLab/blob/master
    // /test11/src/main/java/com/example/test11/MainActivity328.kt
    // 뷰 경로 :
    // https://github.com/lsy3709/AndroidLab/blob/master
    // /test11/src/main/res/menu/menu_328.xml
    // cf. 참고 했지만, 파일을 직접 만들어서 확인함

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 화면에 출력하기 위한 객체 생성.
        // R : Resource
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu11 -> {
            Toast.makeText(this@JetActivity, "menu 11 clicked", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "menu11 click")
            true
        }
        R.id.menu12 -> {
            Toast.makeText(this@JetActivity, "menu 12 clicked", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "menu12 click")
            true
        }
        R.id.menu13 -> {
            Toast.makeText(this@JetActivity, "menu 13 clicked", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "menu13 click")
            true
        }
        R.id.menu2 -> {
            Toast.makeText(this@JetActivity, "menu 2 clicked", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "menu2 click")
            true
        }
        R.id.menu3 -> {
            Toast.makeText(this@JetActivity, "menu 3 clicked", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "menu3 click")
            true
        }
        else -> {
            Toast.makeText(this@JetActivity, "function", Toast.LENGTH_SHORT).show()
            super.onOptionsItemSelected(item)
        }
    }

}