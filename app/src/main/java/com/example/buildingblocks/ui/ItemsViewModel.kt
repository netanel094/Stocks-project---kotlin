package com.example.buildingblocks.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.buildingblocks.Item
import com.example.buildingblocks.data.model.repository.ItemRepository
class ItemsViewModel (application: Application) : AndroidViewModel(application) {

    private  val repository = ItemRepository(application)
    val items : LiveData<List<Item>>? = repository.getItems()

    fun addItem(item: Item){
        repository.addItem(item)
    }

    fun deleteItem(item: Item){
        repository.deleteItem(item)
    }
}