package com.example.test10_12_jjh.test12

import android.app.Application
import com.example.test10_12_jjh.net.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIApplication : Application(){

    var networkService : NetworkService
    var networkService2 : NetworkService
    var networkService3 : NetworkService

    val retrofit : Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://www.khoa.go.kr/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val retrofit2 : Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://www.khoa.go.kr/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val retrofit3 : Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://www.khoa.go.kr/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    init {
        networkService = retrofit.create(NetworkService::class.java)
        networkService2 = retrofit2.create(NetworkService::class.java)
        networkService3 = retrofit3.create(NetworkService::class.java)
    }

}