package com.example.thelibraryapp.mvp.presenters

import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.delegates.AddToShelfCheckboxDelegate
import com.example.thelibraryapp.mvp.views.AddToShelfView
import com.example.thelibraryapp.mvp.views.ShelfView

interface AddToShelfPresenter :
    IBasePresenter,
    AddToShelfCheckboxDelegate {
        fun initView(view: AddToShelfView)
        fun onTapCloseButton()
        fun onTapAddToShelfButton()
        fun getBookExtra(book: BookVO)
}