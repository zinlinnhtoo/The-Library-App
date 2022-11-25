package com.example.thelibraryapp.mvp.views

import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.OverviewListVO

interface HomeView :
    BaseView {
        fun showBookList(overViewList: List<OverviewListVO>)
        fun showBannerCarousel(bookList: List<BookVO>)
        fun showBookOptionBottomSheet(book: BookVO)
        fun navigateToBookDetailScreen(bookJson: String)
        fun navigateToBookCategoryScreen(categoryTitle: String)
        fun navigateToAddToShelfScreen(bookJson: String)
        fun navigateToBookSearchScreen()
}