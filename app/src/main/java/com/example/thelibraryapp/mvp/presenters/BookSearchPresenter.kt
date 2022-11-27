package com.example.thelibraryapp.mvp.presenters

import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.mvp.views.BookSearchView
import io.reactivex.rxjava3.core.Observable

interface BookSearchPresenter :
    IBasePresenter,
    BookViewHolderDelegate {
        fun initView(view: BookSearchView)
        fun onSearchTextChanges(searchText: String): Observable<List<BookVO>>
        fun onSearchBookList(bookList: List<BookVO>)
        fun onThrowableError(errorMessage: String)
}