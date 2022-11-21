package com.example.thelibraryapp.data.models

import android.icu.text.CaseMap.Title
import androidx.lifecycle.LiveData
import com.example.thelibraryapp.data.vos.ShelfVO

interface ShelfModel {

    fun insertShelf(
        shelf: ShelfVO
    )

    fun getAllShelves(): LiveData<List<ShelfVO>>?

    fun getShelf(
        title: String
    ): LiveData<ShelfVO>?

    fun deleteShelf(shelf: ShelfVO)
}