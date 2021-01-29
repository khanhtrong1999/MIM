package com.example.mim
import com.google.gson.annotations.SerializedName

data class Production2(
        @SerializedName("status")
        val status:Boolean,
        @SerializedName("message")
        val message:String,
        @SerializedName("code")
        val code:Int,
        @SerializedName("data")
        val data:List<DataK>
) {
    data class DataK(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("gender")
            val gender: String,
            @SerializedName("price")
            val price: Int,
            @SerializedName("image")
            val image: String,
            @SerializedName("category_id")
            val category_id: Int,
            @SerializedName("created_at")
            val created_at: String,
            @SerializedName("updated_at")
            val updated_at: String,
    )
}
