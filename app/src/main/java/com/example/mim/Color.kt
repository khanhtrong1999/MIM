package com.example.mim

import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("status")
    val status:Boolean,
    @SerializedName("message")
    val message:String,
    @SerializedName("code")
    val code:Int,
    @SerializedName("data")
    val data: List<Color.datas>
){
    data class datas (
        @SerializedName("id")
        val id:Int,
        @SerializedName("name")
        val name:String,
        @SerializedName("created_at")
        var created_at:String,
        @SerializedName("updated_at")
        var updated_at:String
    )
}
