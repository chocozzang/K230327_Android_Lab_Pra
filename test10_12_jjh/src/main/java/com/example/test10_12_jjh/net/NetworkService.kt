package com.example.test10_12_jjh.net

import com.example.test10_12_jjh.model.TideModel
import com.example.test10_12_jjh.model.TidePreModel
import com.example.test10_12_jjh.model.WeatherModel
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

    @GET("VilageFcstInfoService_2.0/getVilageFcst")
    fun getWeather(
        @Query("ServiceKey") serviceKey : String,
        @Query("pageNo") pageNo : Int,
        @Query("numOfRows") numOfRows : Int,
        @Query("dataType") dataType : String,
        @Query("base_date") baseDate : String,
        @Query("base_time") baseTime : String,
        @Query("nx") nx : Int,
        @Query("ny") ny : Int
    ) : retrofit2.Call<WeatherModel>

    @GET("oceangrid/tideObsPre/search.do")
    fun getPreTide(
        @Query("ServiceKey") serviceKey: String,
        @Query("ObsCode") obsCode : String,
        @Query("Date") date : String,
        @Query("ResultType") resultType: String
    ) : retrofit2.Call<TidePreModel>
}