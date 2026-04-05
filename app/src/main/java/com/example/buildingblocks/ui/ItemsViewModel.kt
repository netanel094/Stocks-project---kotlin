package com.example.buildingblocks.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.buildingblocks.Item
import com.example.buildingblocks.data.model.repository.ItemRepository
import com.example.buildingblocks.data.network.NewsItem
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ItemRepository(application)
    val items: LiveData<List<Item>>? = repository.getItems()

    private val _news = MutableLiveData<List<NewsItem>>()
    val news: LiveData<List<NewsItem>> = _news

    private val _isLoadingPrices = MutableLiveData(false)
    val isLoadingPrices: LiveData<Boolean> = _isLoadingPrices

    fun addItem(item: Item) {
        repository.addItem(item)
    }

    fun deleteItem(item: Item) {
        repository.deleteItem(item)
    }

    fun refreshPrices() {
        val currentItems = items?.value ?: return
        if (currentItems.isEmpty()) return
        _isLoadingPrices.value = true
        viewModelScope.launch {
            val symbols = currentItems.joinToString(",") { it.title }
            val quotes = repository.fetchPrices(symbols)
            quotes.forEach { quote ->
                val item = currentItems.find { it.title == quote.symbol }
                if (item != null && quote.regularMarketPrice != null) {
                    val newPrice = "$${String.format("%.2f", quote.regularMarketPrice)}"
                    repository.updateItem(item.copy(price = newPrice))
                }
            }
            _isLoadingPrices.postValue(false)
        }
    }

    fun fetchNews(symbol: String) {
        viewModelScope.launch {
            val result = repository.fetchNews(symbol)
            _news.postValue(result)
        }
    }
}
