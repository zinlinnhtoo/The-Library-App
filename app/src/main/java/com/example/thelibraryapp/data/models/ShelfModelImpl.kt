package com.example.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.example.thelibraryapp.data.vos.ShelfVO

object ShelfModelImpl: BaseModel(), ShelfModel {
    override fun insertShelf(shelf: ShelfVO, onFailure: (String) -> Unit) {
        try {
            mTheLibraryDatabase?.shelfDao()?.insertShelf(shelf)
        } catch (e: Exception) {
            onFailure(e.toString())
        }
    }

    override fun getAllShelves(): LiveData<List<ShelfVO>>? {
        return mTheLibraryDatabase?.shelfDao()?.getAllShelves()
    }
}