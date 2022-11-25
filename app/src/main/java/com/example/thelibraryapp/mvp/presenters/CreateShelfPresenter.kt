package com.example.thelibraryapp.mvp.presenters

import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.mvp.views.CreateShelfView

interface CreateShelfPresenter :
    IBasePresenter {
        fun initView(view: CreateShelfView)
        fun insertSheff(shelf: ShelfVO)
        fun onTapBackButton()
}