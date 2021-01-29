package com.example.mim

import com.google.gson.annotations.SerializedName

data class obj1(
    @SerializedName("status")
    val status:Boolean,
    @SerializedName("message")
    val message:String,
    @SerializedName("code")
    val code:Int,
    @SerializedName("data")
    val data:List<obj2>
){
    data class obj2 (
        @SerializedName("id")
        val id:Int,
        @SerializedName("name")
        val name:String,
        @SerializedName("description")
        val description:String,
        @SerializedName("gender")
        val gender:String,
        @SerializedName("price")
        val price:Int,
        @SerializedName("image")
        val image:List<String>,
        @SerializedName("category_id")
        var category_id:Int,
        @SerializedName("created_at")
        var created_at:String,
        @SerializedName("updated_at")
        var updated_at:String
    )
}
