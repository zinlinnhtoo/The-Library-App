package com.example.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibraryapp.data.models.ShelfModel
import com.example.thelibraryapp.data.models.ShelfModelImpl
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.mvp.views.CreateShelfView

class CreateShelfPresenterImpl: ViewModel(), CreateShelfPresenter {

    private var mView: CreateShelfView? = null
    private val mShelfModel: ShelfModel = ShelfModelImpl

    override fun initView(view: CreateShelfView) {
        mView = view
    }

    override fun insertSheff(shelf: ShelfVO) {
        mShelfModel.insertShelf(shelf)
        mView?.finishScreen()
    }

    override fun onTapBackButton() {
        mView?.onBackPress()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}