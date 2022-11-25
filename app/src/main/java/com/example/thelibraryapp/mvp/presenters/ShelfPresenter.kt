package com.example.thelibraryapp.mvp.presenters

import com.example.thelibraryapp.delegates.ShelfViewHolderDelegate
import com.example.thelibraryapp.mvp.views.ShelfView

interface ShelfPresenter :
    IBasePresenter,
    ShelfViewHolderDelegate {
        fun initView(view: ShelfView)
        fun onTapCreateShelf()
}