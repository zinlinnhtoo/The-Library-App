package com.example.thelibraryapp.mvp.views

import com.example.thelibraryapp.data.vos.BookVO

interface BookDetailView: BaseView {
    fun showBookDetail(book: BookVO)
}