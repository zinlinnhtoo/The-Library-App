package com.example.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibraryapp.data.models.ShelfModel
import com.example.thelibraryapp.data.models.ShelfModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.mvp.views.AddToShelfView
import com.example.thelibraryapp.mvp.views.ShelfView

class AddToShelfPresenterImpl : ViewModel(), AddToShelfPresenter {

    private val mShelfModel: ShelfModel = ShelfModelImpl
    private var mShelfList = mutableListOf<ShelfVO>()
    private var mBook: BookVO? = null

    private var mView: AddToShelfView? = null

    override fun initView(view: AddToShelfView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mShelfModel.getAllShelves()
            ?.observe(owner) {
                mView?.showShelfList(it)
            }
    }

    override fun onCheckedCheckbox(shelf: ShelfVO, isChecked: Boolean) {
        if (isChecked) {
            mShelfList.add(shelf)
        } else {
            mShelfList.remove(shelf)
        }
    }

    override fun onTapCloseButton() {
        mView?.closeAddToShelfScreen()
    }

    override fun onTapAddToShelfButton() {
        mShelfList.forEach { shelf ->
            mBook?.let { book ->
                if (shelf.books?.contains(book) == false) {
                    shelf.books.add(book)
                }
                mShelfModel.insertShelf(shelf)
            }
        }
        mView?.onBackScreen()
    }

    override fun getBookExtra(book: BookVO) {
        mBook = book
    }
}