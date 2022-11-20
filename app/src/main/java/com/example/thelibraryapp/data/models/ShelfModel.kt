package com.example.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.example.thelibraryapp.data.vos.ShelfVO

interface ShelfModel {

    fun insertShelf(
        shelf: ShelfVO,
        onFailure: (String) -> Unit
    )

    fun getAllShelves(
        onFailure: (String) -> Unit
    ): LiveData<List<ShelfVO>>?
}