package com.example.mim

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("status")
    val status:Boolean,
    @SerializedName("message")
    val message:String,
    @SerializedName("code")
    val code:Int
)
