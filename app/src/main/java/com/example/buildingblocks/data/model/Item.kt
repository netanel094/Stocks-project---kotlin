package com.example.buildingblocks
import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Parcelize
@Entity(tableName = "stocks")
data class Item(
    @ColumnInfo(name = "symbol")
    val title:String,
    @ColumnInfo(name = "description")
    val description:String,
    @ColumnInfo(name = "price")
    val price:String,
    @ColumnInfo(name = "photo")
    val photo: Uri?): Parcelable {

        @PrimaryKey(autoGenerate = true)
        var id : Int = 0
    }


//object ItemMenager {
//    val items:MutableList<Item> = mutableListOf()
//
//    fun add(item:Item) {
//        items.add(item)
//    }
//
//    fun remove(index:Int) {
//        items.removeAt(index)
//    }
