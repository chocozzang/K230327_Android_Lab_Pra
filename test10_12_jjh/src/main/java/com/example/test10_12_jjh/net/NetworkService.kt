package com.example.test10_12_jjh.net

import com.example.test10_12_jjh.model.TempModel
import com.example.test10_12_jjh.model.TideModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("oceangrid/tideObsPreTab/search.do")
    fun getTide(
        @Query("ServiceKey") serviceKey : String,
        @Query("ObsCode") obsCode : String,
        @Query("Date") date : String,
        @Query("ResultType") resultType : String
    ) : retrofit2.Call<TideModel>

    @GET("oceangrid/tideObsRecent/search.do")
    fun getTemp(
        @Query("ServiceKey") serviceKey : String,
        @Query("ObsCode") obsCode : String,
        @Query("Date") date : String,
        @Query("ResultType") resultType : String
    ) : retrofit2.Call<TempModel>
}