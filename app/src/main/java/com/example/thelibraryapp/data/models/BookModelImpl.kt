package com.example.thelibraryapp.data.models

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.OverviewListVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
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

    override fun getBookListByListName(
        list: String,
        onSuccess: (List<OverviewListVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mNewYorkTimesApi.getBookByListName(
            list = list
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it.results?.let { it1 -> onSuccess(it1) }
                },
                {
                    onFailure(it.localizedMessage.orEmpty())
                }
            )
    }

    override fun searchGoogleBook(q: String): Observable<List<BookVO>> {
        return mGoogleBookApi.searchGoogleBook(q).map {
            it.items?.map {
                Log.println(Log.INFO, "ItemVO", it.toString())
                it.toBookVO()
            }?: listOf()
        }.onErrorResumeNext {
            Observable.just(listOf())
        }.subscribeOn(Schedulers.io())
    }

}