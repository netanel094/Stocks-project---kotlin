package com.example.buildingblocks.data.model.repository

import android.app.Application
import com.example.buildingblocks.Item
import com.example.buildingblocks.data.model.local_db.ItemDao
import com.example.buildingblocks.data.model.local_db.ItemDatabase

class ItemRepository (application: Application){
    private var itemDao:ItemDao?

    init {
        val db = ItemDatabase.getDatabase(application.applicationContext)
        itemDao = db?.itemsDao()
    }

    fun getItems() = itemDao?.getItems()

    fun addItem(item:Item){

        itemDao?.addItem(item)
    }

    fun deleteItem(item:Item){

        itemDao?.deleteItem(item)
    }

    fun getItem(symbol:String) = itemDao?.getItem(symbol)
}