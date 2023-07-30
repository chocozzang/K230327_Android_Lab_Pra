package com.example.test10_12_jjh.test12

import android.app.Application
import com.example.test10_12_jjh.net.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIApplication : Application(){

    var networkService : NetworkService

    val retrofit : Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://www.khoa.go.kr/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    init {
        networkService = retrofit.create(NetworkService::class.java)
    }
}