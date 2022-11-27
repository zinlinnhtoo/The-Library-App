package com.example.thelibraryapp.mvp.views

import com.example.thelibraryapp.data.vos.BookVO

interface BookCategoryView
    : BaseView {
        fun showBookList(bookList: List<BookVO>)
        fun showCategoryBottomSheet()
        fun navigateToBookDetail(book: BookVO)
        fun setUpCategoryTitle(categoryTitle: String)
}