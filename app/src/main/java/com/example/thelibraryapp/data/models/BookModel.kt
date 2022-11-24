package com.example.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.OverviewListVO
import io.reactivex.rxjava3.core.Observable

interface BookModel {
    fun getOverview(
        onFailure: (String) -> Unit
    ): LiveData<List<OverviewListVO>>?

    fun insertBook(
        book: BookVO,
        onFailure: (String) -> Unit
    )

    fun getReadBook(): LiveData<List<BookVO>>?

    fun getBookListByListName(
        list: String,
        onSuccess: (List<OverviewListVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun searchGoogleBook(
        q: String
    ): Observable<List<BookVO>>
}