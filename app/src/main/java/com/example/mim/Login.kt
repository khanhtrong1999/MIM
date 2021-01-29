package com.example.mim

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("status")
    val status:Boolean,
    @SerializedName("message")
    val message:String,
    @SerializedName("code")
    val code:Int,
    @SerializedName("data")
    val data:datas
){
    data class datas (
        @SerializedName("id")
        val id:Int,
        @SerializedName("email")
        val email:String,
        @SerializedName("status")
        val status:Int,
        @SerializedName("token")
        val token:String,
        @SerializedName("created_at")
        var created_at:String,
        @SerializedName("updated_at")
        var updated_at:String
    )
}
