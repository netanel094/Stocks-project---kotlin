package com.example.buildingblocks.data.model.repository

import android.app.Application
import com.example.buildingblocks.Item
import com.example.buildingblocks.data.model.local_db.ItemDao
import com.example.buildingblocks.data.model.local_db.ItemDatabase
import com.example.buildingblocks.data.network.NewsItem
import com.example.buildingblocks.data.network.Quote
import com.example.buildingblocks.data.network.RetrofitInstance

class ItemRepository(application: Application) {
    private var itemDao: ItemDao?

    init {
        val db = ItemDatabase.getDatabase(application.applicationContext)
        itemDao = db?.itemsDao()
    }

    fun getItems() = itemDao?.getItems()

    fun addItem(item: Item) {
        itemDao?.addItem(item)
    }

    fun deleteItem(item: Item) {
        itemDao?.deleteItem(item)
    }

    fun updateItem(item: Item) {
        itemDao?.updateItem(item)
    }

    fun getItem(symbol: String) = itemDao?.getItem(symbol)

    suspend fun fetchPrices(symbols: String): List<Quote> {
        return try {
            RetrofitInstance.api.getQuotes(symbols).quoteResponse?.result ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun fetchNews(symbol: String): List<NewsItem> {
        return try {
            RetrofitInstance.api.getNews(symbol).news ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
