package com.example.buildingblocks.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(request)
        }
        .build()

    val api: StockApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://query1.finance.yahoo.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockApiService::class.java)
    }
}
