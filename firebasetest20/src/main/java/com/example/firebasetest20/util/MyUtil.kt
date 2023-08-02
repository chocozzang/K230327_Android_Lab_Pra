package com.example.firebasetest20.util

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Date

// 해당 코틀린 파일은 함수를 리팩토링 하여 파일로써 따로 분리하여 자주 사용할 수 있도록 함
// 매개변수의 타입이 액티비티로 사용이 가능한 함수임
fun myCheckPermission(activity: AppCompatActivity) {

    // 인탠트하는 후처리함수
    // 권한 여부를 확인하는 후처리 기능임
    // ActivityResultContracts.RequestPermission()
    val requestPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            // 앱 시작시 미디어 이미지 저장소에 접근이 가능한가에 대한 여부를 토스트 메세지로 나타냄
            Toast.makeText(activity, "권한 승인", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "권한 거부", Toast.LENGTH_SHORT).show()
        }
    }

    // 접근 권한을 READ_MEDIA_IMAGES로 변경하였음
    if (ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_MEDIA_IMAGES
        ) !== PackageManager.PERMISSION_GRANTED
    ) {
        requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
    }
}

fun dateToString(date: Date): String {
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}

// 이미지 압축 기능은 아직 구현된 것은 아님
