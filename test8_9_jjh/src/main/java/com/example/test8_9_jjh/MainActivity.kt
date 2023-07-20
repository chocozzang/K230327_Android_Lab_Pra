package com.example.test8_9_jjh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.example.test8_9_jjh.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val TAG : String = "TEST8_9"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩이 없을 때
        //setContentView(R.layout.activity_main)

        // 이벤트 핸들러 처리하는 방법 3가지. 그 중에서 SAM 기법, Single Abstract Method
        // 추상 메서드가 하나인 인터페이스 -> 바로 람다식으로 사용 가능한 기법.
        // https://github.com/lsy3709/AndroidLab/blob/master/test8/src/main/java/com/example/test8/MainActivity221.kt
        // 사전 작업.
        // 새로 만든 모듈에는, 빌드.그레들 파일에 뷰바인딩 설정이 없음. 설정
        // 참고, 앞에 모듈에 설정코드, 복붙,
        // 체크박스 뷰, 버튼 뷰 설정.
        // 리스너 설정, 결과 확인.
        // 바인딩을 할 때

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /* **체크 박스 이벤트**
        binding.checkbox1.setOnCheckedChangeListener {
                compoundButton, b -> Log.d(TAG, "체크박스 클릭11")
        }
        binding.checkbox2.setOnCheckedChangeListener {
                compoundButton, b -> Log.d(TAG,"체크박스 클릭22")
        }*/

        // **롱 클릭 이벤트**
        // 바인딩은 상단에 진행했음
        binding.buttontest?.setOnClickListener {
            Log.d(TAG, "ONCLICKLISTNER") }
        binding.buttontest?.setOnLongClickListener {
            Log.d(TAG, "ONLONGCLICKLISTNER")
            true}

    }

    // **터치 이벤트**
    // 소스 경로 :
    // https://github.com/lsy3709/AndroidLab/blob/master/test8/src/main/java/com/example/test8/MainActivity.kt
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                // 로깅관련 d - debug, e - error,
                Log.d(TAG,
                    "Touch down event x: ${event.x}, rawX: ${event.rawX}, y : ${event.y}, rawY : ${event.rawY}")
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG,
                    "Touch up event event x: ${event.x}, rawX: ${event.rawX}, y : ${event.y}, rawY : ${event.rawY}")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG,
                    "Touch move event event x: ${event.x}, rawX: ${event.rawX}, y : ${event.y}, rawY : ${event.rawY}")
            }
        }
        return super.onTouchEvent(event)
    }

    // **키 이벤트**
    // 소스 경로 :
    // https://github.com/lsy3709/AndroidLab/blob/master/test8/src/main/java/com/example/test8/MainActivity218.kt
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> Log.d(TAG, "BACK Button을 눌렀네요")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d(TAG, "Volume Up 키를 눌렀네요")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d(TAG, "Volume Down 키를 눌렀네요")
            KeyEvent.KEYCODE_ENTER -> Log.d(TAG, "Enter 키를 눌렀네요 ${KeyEvent.KEYCODE_ENTER}")
        }
        return super.onKeyDown(keyCode, event)
    }

    // 백 키에 대한 별도 함수
    /*
    override fun onBackPressed() {
        Log.d(TAG, "Pressed Back Button!!!")
    }
    */

    // 뷰 바인딩을 하지 않고 확인하는 예제 코드
    // 경로 - 공식문서
    // https://developer.android.com/guide/topics/ui/controls/checkbox?hl=ko
    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.checkbox1 -> {
                    if (checked) {
                        Toast.makeText(this@MainActivity,"체크박스1 선택",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity,"체크박스1 해제",Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.checkbox2 -> {
                    if (checked) {
                        Toast.makeText(this@MainActivity,"체크박스2 선택",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity,"체크박스2 해제",Toast.LENGTH_SHORT).show()
                    }
                }
                // TODO: Veggie sandwich
            }
        }
    }
}