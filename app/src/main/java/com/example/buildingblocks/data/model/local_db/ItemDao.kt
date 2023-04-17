package com.example.buildingblocks.data.model.local_db
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import androidx.room.Insert
import com.example.buildingblocks.Item


@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(item: Item)

    @Delete()
    fun deleteItem(vararg items: Item)

    @Update()
    fun updateItem(item:Item)

    @Query("SELECT * FROM stocks")
    fun getItems():LiveData<List<Item>>

    @Query("SELECT * FROM stocks where symbol LIKE :symbol")
    fun getItem(symbol:String):Item
}