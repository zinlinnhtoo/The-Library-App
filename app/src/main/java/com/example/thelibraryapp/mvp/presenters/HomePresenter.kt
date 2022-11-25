package com.example.thelibraryapp.mvp.presenters

import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.GoToCategoryDelegate
import com.example.thelibraryapp.mvp.views.HomeView

interface HomePresenter :
    IBasePresenter,
    BookOptionDelegate,
    GoToCategoryDelegate,
    BookViewHolderDelegate {
    fun initView(view: HomeView)
    fun onTapAddToShelf(book: BookVO)
    fun onTapSearchBar()
}