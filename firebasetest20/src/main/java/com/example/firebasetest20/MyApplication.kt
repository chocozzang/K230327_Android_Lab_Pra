package com.example.firebasetest20

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class MyApplication: MultiDexApplication() {
    // dex, 코틀린으로 컴파일할 때 추가되는 파일 구조
    // java -> class -> dex, jvm, ART(Android Run Time)
    companion object { // 클래스 변수로 사용한다는 의미 -> static과 유사함
        // lateinit으로 선언되어있어 정의만 해두고
        // 실제로 값이 초기화 된 것은 아님
        // onCreate 함수에서 초기화함
        lateinit var auth: FirebaseAuth
        var email: String? = null
        lateinit var db: FirebaseFirestore
        lateinit var storage: FirebaseStorage
        // 파이어베이스의 인증을 이용
        // auth.currentUser -> 파이어베이스로 인증된 사용자
        // 로그인 후에 인증된 유저를 가리킴
        fun checkAuth(): Boolean {
            var currentUser = auth.currentUser
            return currentUser?.let {
                email = currentUser.email
                // 이메일 유효성 여부 체크
                currentUser.isEmailVerified
            } ?: let {
                false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage
    }
}