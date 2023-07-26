package com.example.test13_16_17_18.test16

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.test13_16_17_18.databinding.ActivityMain2Binding

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test16/src/main/java/com/example/test16/MainActivity.kt
// 뷰 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test16/src/main/res/layout/activity_main.xml
class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    // 인텐트, 후처리 2가지 ActivityResultLauncher
    // 연락처(외부앱) 여기에 접근해서, 권한을 획득하고, 다시 돌아와서 처리함
    lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    lateinit var requestContactsLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 후처리하는 코드부분.
        requestPermissionLauncher = registerForActivityResult(
            // RequestMultiplePermissions : 이 부분에는 다른 작업도 들어감
            // 권한 획득 외에, 데이터를 가져오는 작업을 더 많이 하게 됨
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            // 후처리의 결과는 map 형태의 collection에 담아서 오게됨
            // Map -> key와 value의 형태로 제공함.
            // 데이터를 it으로 계속 받아옴
            // 아래의 런처 함수를 수행해서 작업이 성공하는 경우에
            // 후처리는 여기서 받아서 작업을 진행하게 됨
            for (entry in it.entries) {
                if(entry.key == "android.permission.READ_CONTACTS" && entry.value) {
                    Log.d("test16", "granted ok...")
                    // intent -> 주로 시스템에 있는 값을 받아옴
                    val intent =
                        Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                    // 인텐트 후처리하는 런처 함수를 실행하면,
                    //
                    requestContactsLauncher.launch(intent)
                }else {
                    Toast.makeText(this, "required permission..", Toast.LENGTH_SHORT).show()
                }
            }

        }

        // 후처리 작업 2 :
        // 연락처의 정보 중에서 특정 정보를 가져오는 쿼리
        requestContactsLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            // 데이터는 it으로 받게됨
            if(it.resultCode == RESULT_OK){
                Log.d("test16", "${it.data?.data}")
                // 프로바이더에서 제공된 데이터에 접근하기 위한 함수 중에 하나
                // contentResolver.query(원본데이터, 보고자 하는 속성들, 조건...) 기본은 오름차순
                // select name, email from user where email = 'lsy@naver.com' and name = 'lsy'
                val cursor = contentResolver.query(
                    it!!.data!!.data!!,
                    arrayOf<String>(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ),
                    null,
                    null,
                    null
                )
                // 테이블의 형태로 받아오므로
                // 반복문으로 받아오면 됨
                Log.d("test16", "cursor size....${cursor?.count}")
                if (cursor!!.moveToFirst()) {
                    val name = cursor?.getString(0)
                    val phone = cursor?.getString(1)
                    binding.resultContact.text = "name: $name, phone: $phone"
                }
            }
        }
        // 시작점
        binding.contactButton.setOnClickListener {
            // 권한을 확인하는 함수
            if (ContextCompat.checkSelfPermission(
                    this,
                    // 허용하는 앱의 문자열이 일치해야 원하는 정보를 가져올 수 있음
                    "android.permission.READ_CONTACTS"
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(arrayOf("android.permission.READ_CONTACTS"))
            } else {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                requestContactsLauncher.launch(intent)
            }
        }
    }
}