package com.example.mim

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {
    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
        .build()
    var retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.122:80/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    var service: APIInterface = retrofit.create(APIInterface::class.java)
}