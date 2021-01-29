package com.example.mim

import com.google.gson.annotations.SerializedName

data class Order(
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
        @SerializedName("total")
        val total:Int,
        @SerializedName("status")
        val status:Int,
        @SerializedName("quantity")
        val quantity:Int,
        @SerializedName("address")
        val address:String,
        @SerializedName("user_id")
        val user_id:Int,
        @SerializedName("qty")
        var qty:Int,
        @SerializedName("created_at")
        var created_at:String,
        @SerializedName("updated_at")
        var updated_at:String,
        @SerializedName("price")
        val price:Int,
        @SerializedName("color")
        val color:String,
        @SerializedName("size")
        val size:Int,
        @SerializedName("order_id")
        val order_id:Int,
        @SerializedName("product_id")
        val product_id:Int,
        @SerializedName("name")
        val name:String,
        @SerializedName("description")
        var description:String,
        @SerializedName("gender")
        var gender:Int,
        @SerializedName("image")
        var image:String,
        @SerializedName("category_id")
        var category_id:Int
    )
}
