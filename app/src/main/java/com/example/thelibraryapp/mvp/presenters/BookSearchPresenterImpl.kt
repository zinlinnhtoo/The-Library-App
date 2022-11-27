package com.example.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibraryapp.data.models.BookModel
import com.example.thelibraryapp.data.models.BookModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.mvp.views.BookSearchView
import io.reactivex.rxjava3.core.Observable

class BookSearchPresenterImpl :
    ViewModel(),
    BookSearchPresenter {

    private var mView: BookSearchView? = null
    private var mBookModel: BookModel = BookModelImpl


    override fun onTapBook(book: BookVO) {
        mView?.navigateToBookDetail(book)
        mBookModel.insertBook(book) {
            mView?.showError(it)
        }
    }

    override fun onSearchBookList(bookList: List<BookVO>) {
        mView?.showSearchedBookList(bookList)
    }

    override fun initView(view: BookSearchView) {
        mView = view
    }

    override fun onSearchTextChanges(searchText: String): Observable<List<BookVO>> {
        return mBookModel.searchGoogleBook(searchText)
    }

    override fun onThrowableError(errorMessage: String) {
       mView?.showError(errorMessage)
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}