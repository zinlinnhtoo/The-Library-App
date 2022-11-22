package com.example.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.example.thelibraryapp.data.vos.ShelfVO

interface ShelfModel {

    fun insertShelf(
        shelf: ShelfVO
    )

    fun getAllShelves(): LiveData<List<ShelfVO>>?

    fun getShelf(
        title: String
    ): LiveData<ShelfVO?>?

    fun renameShelf(
        newTitle: String,
        oldTitle: String
    )

    fun deleteShelf(shelf: ShelfVO)
}