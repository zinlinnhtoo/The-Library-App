package com.example.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibraryapp.data.models.ShelfModel
import com.example.thelibraryapp.data.models.ShelfModelImpl
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.mvp.views.ShelfView

class ShelfPresenterImpl: ViewModel(), ShelfPresenter {

    private var mView: ShelfView? = null
    private var mShelfModel: ShelfModel = ShelfModelImpl

    override fun initView(view: ShelfView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mShelfModel.getAllShelves()?.observe(owner) {
            mView?.showShelfList(it)
        }
    }

    override fun onTapShelf(shelf: ShelfVO) {
        mView?.navigateToShelfDetail(shelf)
    }

    override fun onTapCreateShelf() {
        mView?.navigateToCreateShelfScreen()
    }
}