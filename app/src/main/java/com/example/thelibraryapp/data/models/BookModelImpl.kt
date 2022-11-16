package com.example.thelibraryapp.data.models

import android.content.Context
import com.example.thelibraryapp.data.vos.OverviewListVO
import com.example.thelibraryapp.persistance.TheLibraryDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object BookModelImpl : BaseModel(), BookModel {

    private var mLibraryDatabase: TheLibraryDatabase? = null

    fun initDatabase(context: Context) {
        mLibraryDatabase = TheLibraryDatabase.getDBInstance(context)
    }

    override fun getOverview(
        onSuccess: (List<OverviewListVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mLibraryDatabase?.overviewDao()?.getAllOverviewList() ?: listOf())
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
                mLibraryDatabase?.overviewDao()?.insertOverview(overviewList)
                onSuccess(overviewList)
            },{
                onFailure(it.localizedMessage.orEmpty())
            })

//        onSuccess = {
//            it.forEach { overviewListVO ->
//                overviewListVO.books?.forEach { bookVO ->
//                    bookVO.bookCategory = overviewListVO.listName
//                }
//            }
//            mLibraryDatabase?.overviewDao()?.insertOverview(it)
//            onSuccess(it)
//        },
//        onFailure
    }

}