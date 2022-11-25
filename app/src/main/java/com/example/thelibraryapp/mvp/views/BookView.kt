package com.example.thelibraryapp.mvp.views

import com.example.thelibraryapp.data.vos.BookVO

interface BookView :
    BaseView{
        fun showBookList(booList: List<BookVO>)
        fun navigateToAddToShelf(book: BookVO)
        fun navigateToBookDetail(book: BookVO)
}