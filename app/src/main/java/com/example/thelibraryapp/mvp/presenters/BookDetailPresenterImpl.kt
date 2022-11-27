package com.example.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.mvp.views.BookDetailView

class BookDetailPresenterImpl: ViewModel(), BookDetailPresenter {

    private var mView: BookDetailView? = null
    private var mBook: BookVO? = null

    override fun initView(view: BookDetailView) {
        mView = view
    }

    override fun getExtraBook(book: BookVO) {
        mBook = book
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mBook?.let { mView?.showBookDetail(it) }
    }
}