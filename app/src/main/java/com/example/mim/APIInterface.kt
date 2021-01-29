package com.example.mim

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*


interface APIInterface {
    @GET("home")
    fun getHome():Call<obj1>

    @GET("men")
    fun getMen():Call<obj1>

    @GET("women")
    fun getWomen():Call<obj1>

    @GET("color")
    fun getColor():Call<Color>

    @GET("size")
    fun getSize():Call<Size>

    @GET("categoryShow/{id}")
    fun getCategoryShow(@Path("id") id: Int):Call<Production2>

    @GET("category")
    fun getCategory():Call<Category>

    @FormUrlEncoded
    @POST("login")
    fun getLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ):Call<Login>

    @FormUrlEncoded
    @POST("register")
    fun getRegister(
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ):Call<Register>

    @GET("product/{id}")
    fun getProduction(@Path("id") id: Int):Call<Production>

    @GET("order/{id}")
    fun getOrder(@Path("id") id: Int):Call<Order>

    @GET("showPending/{id}")
    fun getPending(@Path("id") id: Int):Call<Order>

    @GET("showVerified/{id}")
    fun getVerified(@Path("id") id: Int):Call<Order>

    @GET("showShipped/{id}")
    fun getShipped(@Path("id") id: Int):Call<Order>

    @GET("search/{name}")
    fun getSearch(@Path("name") id: String):Call<obj1>

    @POST("orderPro")
    fun postOrder(@Body data: JsonObject?): Call<Status?>?

    @FormUrlEncoded
    @POST("profile/update")
    fun getUpdateProfile(
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("avatar") avatar: String,
        @Field("gender") gender: Int,
        @Field("phone") phone: String,
        @Field("birthday") birthday: String,
        @Field("address") address: String,
        @Field("id") id: Int,
    ):Call<Profile>

    @POST("profile")
    fun getSelectProfile(
        @Field("user_id") user_id: Int,
    ):Call<Profile>
}