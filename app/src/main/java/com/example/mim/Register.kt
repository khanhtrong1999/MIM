package com.example.mim

import com.google.gson.annotations.SerializedName

data class Register(
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
        var updated_at:String,
        @SerializedName("profile")
        val data:profile
    )
    data class profile(
        @SerializedName("id")
        val id:Int,
        @SerializedName("first_name")
        val first_name:String,
        @SerializedName("last_name")
        val last_name:String,
        @SerializedName("avatar")
        val avatar:String,
        @SerializedName("birthday")
        var birthday:String,
        @SerializedName("gender")
        var gender:String,
        @SerializedName("phone")
        val phone:String,
        @SerializedName("address")
        val address:String,
        @SerializedName("user_id")
        val user_id:Int,
        @SerializedName("created_at")
        val created_at:String,
        @SerializedName("updated_at")
        var updated_at:String,
        @SerializedName("full_name")
        var full_name:String,
    )
}
