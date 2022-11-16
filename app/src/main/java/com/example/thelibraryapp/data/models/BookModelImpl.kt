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

    override fun getBooks(onFailure: (String) -> Unit): LiveData<List<BookVO>>? {
//        val overViewList = mTheLibraryDatabase?.overviewDao()?.getAllOverviewList()
//        val bookList = mTheLibraryDatabase?.overviewDao()?.getAllBooks()
//        mTheLibraryDatabase?.bookDao()?.insertBook(bookList ?: listOf())
//
//        return mTheLibraryDatabase?.bookDao()?.getAllBooks()

        val overViewList = mTheLibraryDatabase?.overviewDao()?.getAllOverviewListOneTime()
        val bookList = mutableListOf<BookVO>()
        overViewList?.forEach { overviewListVO ->
            overviewListVO.books?.forEach { bookVO ->
                bookList.add(bookVO)
            }
        }
        Log.println(Log.INFO, "bookList", "$bookList")
        mTheLibraryDatabase?.bookDao()?.insertBook(bookList)

        return mTheLibraryDatabase?.bookDao()?.getAllBooks()
    }

}