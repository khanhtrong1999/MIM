package com.example.mim

import com.google.gson.annotations.SerializedName

data class Production(
    @SerializedName("status")
    val status:Boolean,
    @SerializedName("message")
    val message:String,
    @SerializedName("code")
    val code:Int,
    @SerializedName("data1")
    val data:DataK,
    @SerializedName("data2")
    val data2:List<DataK>
){
    data class DataK(
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
        val category_id:Int,
        @SerializedName("created_at")
        val created_at:String,
        @SerializedName("updated_at")
        val updated_at:String,
        @SerializedName("colors")
        val colors:List<ColorAndSize>,
        @SerializedName("sizes")
        val sizes:List<ColorAndSize>,
    ){
        data class ColorAndSize(
               val id:Int,
               val name:String,
               val created_at:String,
               val updated_at:String,
               val pivot:Pivot
        ){
            data class Pivot(
                val product_id:Int,
                val color_id:Int,
                val created_at:String,
                val updated_at:String
            )
        }
    }
}
