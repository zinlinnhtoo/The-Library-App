package com.example.thelibraryapp.mvp.presenters

import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.CategoryBookOptionDelegate
import com.example.thelibraryapp.mvp.views.BookCategoryView

interface BookCategoryPresenter :
    IBasePresenter,
    CategoryBookOptionDelegate,
    BookViewHolderDelegate {
        fun initView(view: BookCategoryView)
        fun getExtraCategoryString(categoryTitle: String)
}