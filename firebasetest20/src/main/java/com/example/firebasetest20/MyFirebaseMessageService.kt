package com.example.firebasetest20

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

// 서비스 컴포넌트 -> 백그라운드에서 동작을 처리하는 클래스
// ex. 알람
// 주의사항 - 화면에 관련된 부분은 건들지 않음 (화면은 액태비티 클래스가 처리하도록 되어있음)
class MyFirebaseMessageService : FirebaseMessagingService() {

    // 새 토큰을 발급받는 함수
    // 이 토큰으로 유저를 구분함
    // 원격지에서 상대방에서 메시지를 보내거나 알림을 보낼 때 이 토큰이 필요함
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("kkang","fcm token....$p0")
    }
     
    // FCM 메시지를 받는 함수. 제목-내용으로 구분함
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d("kkang", "fcm message...... ${p0.notification}")
        Log.d("kkang", "fcm message...... ${p0.data}")
    }
}