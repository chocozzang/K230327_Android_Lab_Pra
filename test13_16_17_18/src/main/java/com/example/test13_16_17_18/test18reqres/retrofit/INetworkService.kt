package com.example.test13_16_17_18.test18reqres.retrofit

import com.example.test13_16_17_18.test18reqres.Model.UserListModel
import com.example.test13_16_17_18.test18reqres.Model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

// 레트로핏 구조 특성상 전달을 인터페이스로 진행
// 인터페이스 -> 추상 함수들의 모음집
interface INetworkService {
    // 실제 백엔드 서버와 주소를 사용하기 위한 URI
    // https://reqres.in/ --> 백엔드(스프링) ==> REST Controller 정의가 되어있음
    // 로컬 상에서 작업 시 http://localhost:8080/reqres.in/
    @GET("api/users")
    // baseurl : https://reqres.in/
    // https://reqres.in/api/users?page=2
    // 예를 들어서 doGetUserList("2")
    // ***** 반환값 중요 ***** //
    // Retrofit2 함수의 반환 타입은 Call 타입
    // -> 제너릭으로 설정이 된 타입을 잘 봐야함
    fun doGetUserList(@Query("page") page: String): Call<UserListModel>
    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>

    //    @GET("users/list?sort=desc")
    @GET("api/users/2")
    fun test1(): Call<UserModel>
}