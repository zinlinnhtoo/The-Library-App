package com.example.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibraryapp.data.models.BookModel
import com.example.thelibraryapp.data.models.BookModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.mvp.views.HomeView
import com.google.gson.Gson

class HomePresenterImpl :
    ViewModel(),
    HomePresenter {

    var mView: HomeView? = null
    var mBookModel: BookModel = BookModelImpl

    override fun initView(view: HomeView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mBookModel.getOverview { mView?.showError(it) }?.observe(owner) {
            mView?.showBookList(it)
        }

        mBookModel.getReadBook()?.observe(owner) {
            mView?.showBannerCarousel(it.reversed())
        }
    }

    override fun onTapBookOption(book: BookVO) {
        mView?.showBookOptionBottomSheet(book)
    }

    override fun onTapBook(book: BookVO) {
        val bookJson = Gson().toJson(book)
        mBookModel.insertBook(book) {
            mView?.showError(it)
        }
        mView?.navigateToBookDetailScreen(bookJson)
    }

    override fun onTapCategory(categoryTitle: String) {
        mView?.navigateToBookCategoryScreen(categoryTitle)
    }

    override fun onTapAddToShelf(book: BookVO) {
        val bookJson = Gson().toJson(book)
        mView?.navigateToAddToShelfScreen(bookJson)
    }

    override fun onTapSearchBar() {
        mView?.navigateToBookSearchScreen()
    }
}