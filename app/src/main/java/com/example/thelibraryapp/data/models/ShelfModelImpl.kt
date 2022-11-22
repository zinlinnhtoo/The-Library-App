package com.example.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.example.thelibraryapp.data.vos.ShelfVO

object ShelfModelImpl: BaseModel(), ShelfModel {
    override fun insertShelf(shelf: ShelfVO) {
        mTheLibraryDatabase?.shelfDao()?.insertShelf(shelf)
    }

    override fun getAllShelves(): LiveData<List<ShelfVO>>? {
        return mTheLibraryDatabase?.shelfDao()?.getAllShelves()
    }

    override fun getShelf(title: String): LiveData<ShelfVO?>? {
        return mTheLibraryDatabase?.shelfDao()?.getShelf(title)
    }

    override fun renameShelf(newTitle: String, oldTitle: String) {
        mTheLibraryDatabase?.shelfDao()?.renameShelf(newTitle, oldTitle)
    }

    override fun deleteShelf(shelf: ShelfVO) {
        mTheLibraryDatabase?.shelfDao()?.deleteShelf(shelf.title)
    }
}