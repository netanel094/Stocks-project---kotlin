package com.example.buildingblocks.data.network

data class QuoteResponse(val quoteResponse: QuoteResult?)
data class QuoteResult(val result: List<Quote>?)
data class Quote(
    val symbol: String,
    val regularMarketPrice: Double?,
    val regularMarketChangePercent: Double?
)

data class NewsResponse(val news: List<NewsItem>?)
data class NewsItem(
    val title: String,
    val link: String,
    val publisher: String,
    val providerPublishTime: Long
)
