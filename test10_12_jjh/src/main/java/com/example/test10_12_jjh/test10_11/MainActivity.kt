package com.example.test10_12_jjh.test10_11

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.test10_12_jjh.databinding.ActivityMainBinding
import com.example.test10_12_jjh.databinding.DialogSampleBinding

class MainActivity : AppCompatActivity() {
    // 바인딩 사용 세팅,
    // 1) build.gradle에서 설정
    lateinit var binding : ActivityMainBinding
    val TAG : String = "test10_12"

    // 알림 다이얼로그에서 예 혹은 아니오를 선택 시,
    // 추가적인 작업을 설정하는 이벤트 핸들러임
    val eventHandler = object : DialogInterface.OnClickListener {
        override fun onClick(p0: DialogInterface?, p1: Int) {
            if (p1 == DialogInterface.BUTTON_POSITIVE) {
                Toast.makeText(this@MainActivity, "positive button click", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "positive button click")
            } else if (p1 == DialogInterface.BUTTON_NEGATIVE) {
                Toast.makeText(this@MainActivity, "negative button click", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "negative button click")

            }
        }
    }

    // onCreate : 최초 1회 실행
    // 특징 : 매개변수로 번들 타입의 객체를 가짐
    // 번들 타입의 객체 : 메모리 상에 임시로 저장해두는 파일
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate : 인스턴스화를 해줌
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 화면에 출력하는 역할
        setContentView(binding.root)

        // 권한 관련 설정
        // 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test10/src/main/java/com/example/test10/MainActivity.kt

        
        // 인텐트할 때, 후처리 방법에서 더 설명함
        // 인탠트 기본 기능
        // 1) 액티비티 간의 화면 전환 (화면이동)
        // 2) 화면 간 이동 시에 데이터 전달
        // 3) 이동된 화면에서 작업한 데이터들도 원래 화면으로 가져올 수 있음
        // EX. A앱, 갤러리(외부 앱) 접근해서 사진을 선택하고 나서, 다시 A앱에 가져오는 경우
        // EX. A앱, 외부 앱 접근해서 권한을 획득하고 나서 다시 A앱으로 돌아오는 경우
        // 4) 외부(시스템 해당 앱에 접근시) intent-filter -> 명시적으로 설정
        // 아래 예시 코드는 3)을 수행하는 코드임
//        val requestPermissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted ->
//
//            if (isGranted) {
//                Log.d(TAG, "callback, granted..")
//            } else {
//                Log.d(TAG, "callback, denied..")
//            }
//        }
//
//        val status= ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION")
//        if(status == PackageManager.PERMISSION_GRANTED){
//            Log.d(TAG, "granted..")
//        }else {
//            requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
//        }

        showToast()

        // 테스트3, 날짜, 시간 다이얼로그 확인
        binding.button1.setOnClickListener {
            DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    // 현재 로그캣에 입력한 날짜 정보 출력
                    // 뷰에도 출력해보기
                    binding.dateView.text = "year : $p1, month : ${p2+1}, dayOfMonth : $p3"
                    Log.d(TAG, "year : $p1, month : ${p2+1}, dayOfMonth : $p3")
                }
            }, 2020, 8, 21).show()
        }
        binding.button2.setOnClickListener {
            TimePickerDialog(this, object: TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                    binding.timeView.text = "hour : $p1, minute : $p2"
                    Log.d(TAG, "time : $p1, minute : $p2")
                }
            }, 15, 0, true).show()
        }

        //테스트4, 알림 다이얼로그
        // 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test10/src/main/java/com/example/test10/MainActivity282.kt
        // 경고창을 뜨는 이유 -> 임포트 시에 2개의 라이브러리 후보가 생김
        // androidx를 선택. 이유? 호환성 부분.
        // 제트팩 라이브러리를 현재 선호함
        // AlertDialog -> yes no 선택하는 알림창
        binding.button3.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("test dialog")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("OK", eventHandler)
                setNegativeButton("Cancel", eventHandler)
                show()
            }
        }

        // 테스트5. 알림 다이어로그2 - 선택할 수 있는 다이얼로그
        binding.button4.setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(
                    items,
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            Toast.makeText(this@MainActivity, "선택한 과일 : ${items[p1]}", Toast.LENGTH_SHORT).show()
                            Log.d(
                                TAG,
                                "선택한 과일 : ${items[p1]}"
                            )
                        }
                    })
                setPositiveButton("닫기", null)
                show()
            }
        }

        // 테스트6. 알림 다이어로그3 - 커스텀 다이어로그
        binding.button5.setOnClickListener {
            val dialogBinding = DialogSampleBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setTitle("커스텀 다이얼 테스트")
                setView(dialogBinding.root)
                show()
            }
        }
    }
    // end of onCreate 함수

    // 테스트2, 토스트 추가 옵션
    // 보이고 나서의 추가 작업, 사라지고 나서의 추가 작업
    // 경로 :
    // https://github.com/lsy3709/AndroidLab/blob/master
    // /test10/src/main/java/com/example/test10/MainActivity278.kt
    @RequiresApi(Build.VERSION_CODES.R)
    fun showToast() {
        val toast = Toast.makeText(this, "종료하려면 한 번 더 누르세요.", Toast.LENGTH_SHORT)
        toast.addCallback(
            object : Toast.Callback() {
                override fun onToastHidden() {
                    super.onToastHidden()
                    Log.d(TAG, "toast hidden")
                }
                override fun onToastShown() {
                    super.onToastShown()
                    Log.d(TAG, "toast shown")
                }
            })
        toast.show()
    }
}