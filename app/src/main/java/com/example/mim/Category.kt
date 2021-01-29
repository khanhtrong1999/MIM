package com.example.mim

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("status")
    val status:Boolean,
    @SerializedName("message")
    val message:String,
    @SerializedName("code")
    val code:Int,
    @SerializedName("data")
    val data: List<Category.datas>
){
    data class datas (
        @SerializedName("id")
        val id:Int,
        @SerializedName("name")
        val name:String,
        @SerializedName("description")
        val description:String,
        @SerializedName("created_at")
        var created_at:String,
        @SerializedName("updated_at")
        var updated_at:String
    )
}