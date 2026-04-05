package com.example.buildingblocks.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {

    @GET("v7/finance/quote")
    suspend fun getQuotes(@Query("symbols") symbols: String): QuoteResponse

    @GET("v1/finance/search")
    suspend fun getNews(
        @Query("q") symbol: String,
        @Query("newsCount") newsCount: Int = 6,
        @Query("quotesCount") quotesCount: Int = 0
    ): NewsResponse
}
