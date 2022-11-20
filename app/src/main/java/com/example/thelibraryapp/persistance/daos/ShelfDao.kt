package com.example.thelibraryapp.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thelibraryapp.data.vos.ShelfVO

@Dao
interface ShelfDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelf(shelf: ShelfVO?)

    @Query("SELECT * FROM shelves")
    fun getAllShelves(): LiveData<List<ShelfVO>>

    @Query("DELETE FROM shelves")
    fun deleteAllShelves()
}