package com.example.thelibraryapp.mvp.presenters

import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.mvp.views.BookView

interface BookPresenter :
    IBasePresenter,
    BookOptionDelegate,
    BookViewHolderDelegate {
        fun initView(view: BookView)
}