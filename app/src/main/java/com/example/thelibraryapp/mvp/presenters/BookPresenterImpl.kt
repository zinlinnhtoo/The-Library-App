package com.example.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibraryapp.data.models.BookModel
import com.example.thelibraryapp.data.models.BookModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.mvp.views.BookView

class BookPresenterImpl: ViewModel(), BookPresenter {

    var mView: BookView? = null
    var mBookModel: BookModel = BookModelImpl

    override fun initView(view: BookView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mBookModel.getReadBook()?.observe(owner) {
            mView?.showBookList(it)
        }
    }

    override fun onTapBookOption(book: BookVO) {
        mView?.navigateToAddToShelf(book)
    }

    override fun onTapBook(book: BookVO) {
        mView?.navigateToBookDetail(book)
    }
}