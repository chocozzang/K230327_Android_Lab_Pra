package com.example.test13_16_17_18.test18reqres

import android.app.Application
import com.example.test13_16_17_18.test18reqres.retrofit.INetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    // MyApplication : Application() 상속을 받아야함!!!
    //INetworkService 인터페이스 타입의 변수를 선언
    // Retrofit 타입의 객체를 생성
    // retrofit.create(INetworkService::class.java)
    // 위에서 정의한 인터페이스를 구현한 객체를 반환한 값 가지고와서
    // networkService 에 할당함.

    // 주의사항, 사용여부 -> 매니페스트 파일의 <application 태그 내부에
    // name으로 설정해서, 해당 앱을 실행할 때, 메모리상에 등록해서 사용한다.

    // 선언만
    var networkService: INetworkService

    // MyApplication 내에서 retrofit을 사용하기 위함
    // 백엔드 서버의 수조를 설정
    // 데이터를 전달 받는 경우, 중간데이터(JSON) 변환해주기
    // 중간에서 데이터의 직렬화/역직렬화 과정을 해주는 라이브러리를 등록함
    // 사용하는 라이브러리 : gson --> 다른 API를 활용해도 되며, 부품처럼 바꿔끼우면 됨
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    
    // 클래스를 생성하거나 사용하는 경우 항상 init을 바로 호출하므로
    // Application을 사용하는 MainActivity9.kt에서 바로
    // retrofit이 할당된 networkService를 사용할 수 있게 됨
    init { // 클래스를 생성 또는 사용하면, 할당작업.
        networkService = retrofit.create(INetworkService::class.java)
    }
}