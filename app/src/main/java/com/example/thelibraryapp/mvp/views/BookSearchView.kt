package com.example.thelibraryapp.mvp.views

import com.example.thelibraryapp.data.vos.BookVO

interface BookSearchView :
    BaseView {
        fun showSearchedBookList(bookList: List<BookVO>)
        fun navigateToBookDetail(book: BookVO)
}