package com.example.test10_12_jjh.model

import com.google.gson.annotations.SerializedName

data class TempModel (
    var result : TempResult
)

data class TempResult (
    var data : TempInfo,
    var meta : TempMeta
)

data class TempMeta (
    @SerializedName("obs_post_id")
    var postid : String,
    @SerializedName("obs_post_name")
    var postname : String
)

data class TempInfo(
    @SerializedName("recode_time")
    var recoretime : String,
    @SerializedName("wind_speed")
    val windspeed  : String,
    @SerializedName("wind_air")
    val windair  : String,
    @SerializedName("air_temp")
    val airtemp  : String,
    @SerializedName("water_temp")
    val watertemp : String,
    @SerializedName("Salinity")
    val salinity : String
)