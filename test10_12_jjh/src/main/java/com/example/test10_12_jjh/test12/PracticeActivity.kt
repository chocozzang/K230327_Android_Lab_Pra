package com.example.test10_12_jjh.test12

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.databinding.ActivityPracticeBinding
import com.example.test10_12_jjh.fragment.OneFragment
import com.example.test10_12_jjh.fragment.ThreeFragment
import com.example.test10_12_jjh.fragment.TwoFragment
import com.google.android.material.tabs.TabLayoutMediator

// 소스 코드 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /ch12_material/src/main/java/com/example/ch12_material/MainActivity.kt
// 뷰 코드 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /ch12_material/src/main/res/layout/activity_main.xml
// 메뉴 메인 코드 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /ch12_material/src/main/res/menu/menu_main.xml
class PracticeActivity : AppCompatActivity() {
     lateinit var toggle : ActionBarDrawerToggle
     class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
         val fragments: List<Fragment>

         init {
             fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
         }

         override fun getItemCount(): Int = fragments.size

         override fun createFragment(position: Int): Fragment = fragments[position]
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //add......................................
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawer,
            R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager){ tab, position ->
            tab.text = "Tab${(position + 1)}"
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("practice", "create")
        menuInflater.inflate(R.menu.menu_main, menu)
        menuInflater.inflate(R.menu.menu_mynavi, menu)
        //return true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun onNavigationItemSelected(item : MenuItem){
        // onCreateOptionsMenu에서 mynavi 메뉴를 등록하고,
        // onOptionsItemSelected에서 각 id에 대한 기능을 구현함
        Log.d("practice", "selected")
        when(item.itemId) {
            R.id.joseokYebo -> {
                Log.d("practice", "joseokYebo here")
                val intent = Intent(this@PracticeActivity, SixthPracticeRecycle::class.java)
                startActivity(intent)
            }
            R.id.mylogin -> {
                Log.d("practice", "mylogin here")
                val intent = Intent(this@PracticeActivity, SixthPracticeLogin::class.java)
                startActivity(intent)
            }
            R.id.myregister -> {
                Log.d("practice", "myregister here")
                val intent = Intent(this@PracticeActivity, SixthPracticeRegister::class.java)
                startActivity(intent)
            }
            R.id.fishingMap -> {
                Log.d("practice", "fishingmap here")
                val intent = Intent(this@PracticeActivity, SixPracticeGoogleMap::class.java)
                startActivity(intent)

            }
        }
    }
}