package com.example.thelibraryapp.data.models

import android.content.Context
import com.example.thelibraryapp.data.vos.OverviewListVO
import com.example.thelibraryapp.network.dataagents.BookDataAgent
import com.example.thelibraryapp.network.dataagents.BookDataAgentImpl
import com.example.thelibraryapp.persistance.TheLibraryDatabase

object BookModelImpl : BookModel {

    private val mBookDataAgent: BookDataAgent = BookDataAgentImpl
    private var mLibraryDatabase: TheLibraryDatabase? = null

    fun initDatabase(context: Context) {
        mLibraryDatabase = TheLibraryDatabase.getDBInstance(context)
    }

    override fun getOverview(
        onSuccess: (List<OverviewListVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mLibraryDatabase?.overviewDao()?.getAllOverviewList() ?: listOf())
        mBookDataAgent.getOverview(
            onSuccess = {
                it.forEach { overviewListVO ->
                    overviewListVO.books?.forEach { bookVO ->
                        bookVO.bookCategory = overviewListVO.listName
                    }
                }
                mLibraryDatabase?.overviewDao()?.insertOverview(it)
                onSuccess(it)
            },
            onFailure
        )
    }

}