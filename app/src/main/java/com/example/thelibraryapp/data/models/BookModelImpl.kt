package com.example.thelibraryapp.data.models

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.OverviewListVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object BookModelImpl : BaseModel(), BookModel {

    override fun getOverview(onFailure: (String) -> Unit): LiveData<List<OverviewListVO>>? {
        mNewYorkTimesApi.getOverview()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val overviewList = it.results.lists
                overviewList.forEach { overview ->
                    overview.books?.forEach { book ->
                        book.bookCategory = overview.listName
                    }
                }
                mTheLibraryDatabase?.overviewDao()?.insertOverview(overviewList)
            },{
                onFailure(it.localizedMessage.orEmpty())
            })
        return mTheLibraryDatabase?.overviewDao()?.getAllOverviewList()
    }

    override fun insertBook(book: BookVO, onFailure: (String) -> Unit) {
        try {
            mTheLibraryDatabase?.bookDao()?.insertSingleBook(book)
        } catch (e: Exception) {
            onFailure(e.toString())
        }
    }

    override fun getReadBook(): LiveData<List<BookVO>>? {
        return mTheLibraryDatabase?.bookDao()?.getAllBooks()
    }

}