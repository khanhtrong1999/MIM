package com.example.mim

import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("total")
    val total:Double,
    @SerializedName("status")
    val status:Int,
    @SerializedName("quantity")
    val quantity:Int,
    @SerializedName("address")
    val address:String,
    @SerializedName("user_id")
    val user_id:Int,
    @SerializedName("created_at")
    val created_at:String,
    @SerializedName("qty")
    val qty:Int,
    @SerializedName("price")
    val price:Double,
    @SerializedName("color")
    val color:String,
    @SerializedName("size")
    val size:Int,
    @SerializedName("order_id")
    val order_id:Int,
    @SerializedName("product_id")
    val product_id:Int,
    @SerializedName("updated_at")
    val updated_at:String,
)
