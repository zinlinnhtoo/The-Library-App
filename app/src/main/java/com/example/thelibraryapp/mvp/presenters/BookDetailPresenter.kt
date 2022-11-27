package com.example.thelibraryapp.mvp.presenters

import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.mvp.views.BookDetailView

interface BookDetailPresenter : IBasePresenter {
    fun initView(view: BookDetailView)
    fun getExtraBook(book: BookVO)
}