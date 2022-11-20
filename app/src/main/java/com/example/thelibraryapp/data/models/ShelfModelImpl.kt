package com.example.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.example.thelibraryapp.data.vos.ShelfVO

object ShelfModelImpl: BaseModel(), ShelfModel {
    override fun insertShelf(shelf: ShelfVO, onFailure: (String) -> Unit) {
        mTheLibraryDatabase!!.shelfDao()?.insertShelf(shelf)
    }

    override fun getAllShelves(onFailure: (String) -> Unit): LiveData<List<ShelfVO>>? {
        return mTheLibraryDatabase?.shelfDao()?.getAllShelves()
    }
}