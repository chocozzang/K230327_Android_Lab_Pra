package com.example.test10_12_jjh

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.test10_12_jjh.databinding.ActivityNotiBinding

class NotiActivity : AppCompatActivity() {
    lateinit var binding : ActivityNotiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 알림설정, 기본
        // 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test10/src/main/java/com/example/test10/MainActivity297.kt
        binding.button.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )
                //채널에 다양한 정보 설정
                channel.description = "My Channel One Description"
                // 런처 앱 (여러 앱의 목록을 보여주는 액티비티)
                // 해당 아이콘 상단에 알림 갯수 등을 보여줌
                channel.setShowBadge(true)
                // 알림음 설정 부분. 시스템에서 제공하는 기본 알림음 사용중
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                // 설정한 알림을 해당 채널에 적용하는 코드
                channel.setSound(uri, audioAttributes)
                // 카메라 후방 플래시 led 색 표시 여부
                channel.enableLights(true)
                // 플래시 led 색 서렂
                channel.lightColor = Color.RED
                // 알림 진동 여부 옵션
                channel.enableVibration(true)
                // 알림 발생시, 진동의 주기, 강도 설정
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200)
                // 채널을 NotificationManager에 등록
                manager.createNotificationChannel(channel)

                //채널을 이용하여 builder 생성
                builder = NotificationCompat.Builder(this, channelId)
            } else {
                builder = NotificationCompat.Builder(this)
            }

            // 상단 아이콘 모양
            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            // 더미 제목, 메시지가 나오지만, 네트워크 통신 라이브러리를 이용한다면
            // 실제 원격지에서 메세지를 가져오는 상황.
            // 우리는 중간에 메시지를 전달해주는 서버인 firebase의 FCM을 이용함
            // FCM(Firebase Cloud Message) : 사용자, 시스템에서 전달된 메세지를 대신 전달해주는 역할
            // 이 코드에서는 임의의 문자를 전달하였음
            builder.setContentTitle("Content Title")
            builder.setContentText("Content Massage")

            val intent = Intent(this, DetailActivity::class.java)
            val pendingIntent =
                PendingIntent.getActivity(this, 10, intent,
                    PendingIntent.FLAG_IMMUTABLE)
            builder.setContentIntent(pendingIntent)
            builder.setAutoCancel(true)

            // 오류 : 1차 문법 체크 API 13이상을 쓴다면, 매니페스트에 권한 설정을 해줘야함.
            // 시스템에서 제공해주는 액션으로 처리함
            // 실제 매니페스트에 추가된 코드 :
            // <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
            manager.notify(11, builder.build())
        }
    }
}