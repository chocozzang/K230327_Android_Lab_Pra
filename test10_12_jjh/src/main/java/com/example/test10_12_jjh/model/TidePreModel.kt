package com.example.test10_12_jjh.model

import com.google.gson.annotations.SerializedName

data class TidePreModel (
    var result : PreResult
)

data class PreResult (
    var data : List<PreTideInfo>,
    var meta : PreMeta
)

data class PreMeta (
    @SerializedName("obs_post_id")
    var postid : String,
    @SerializedName("obs_post_name")
    var postname : String
)

data class PreTideInfo(
    @SerializedName("tide_level")
    var tidelevel : String,
    @SerializedName("recode_time")
    var recodetime : String
)