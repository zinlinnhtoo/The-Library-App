package com.example.thelibraryapp.mvp.views

import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ShelfVO

interface ShelfDetailView: BaseView {
    fun showBookListOnShelf(bookList: List<BookVO>)
    fun showShelfList(shelf: ShelfVO)
//    fun showRenamedShelf(shelf: ShelfVO)
    fun showBottomSheet(book: BookVO)
    fun navigateToBookDetail(book: BookVO)
    fun navigateToAddToShelfScreen(bookJson: String)
}