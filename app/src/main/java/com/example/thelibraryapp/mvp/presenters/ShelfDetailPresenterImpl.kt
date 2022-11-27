package com.example.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibraryapp.data.models.ShelfModel
import com.example.thelibraryapp.data.models.ShelfModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.mvp.views.ShelfDetailView

class ShelfDetailPresenterImpl : ViewModel(), ShelfDetailPresenter {

    private var mShelf: ShelfVO? = null

    private var mView: ShelfDetailView? = null

    private val mShelfModel: ShelfModel = ShelfModelImpl

    override fun initView(view: ShelfDetailView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mShelf?.let {
            mShelfModel.getShelf(it.title)?.observe(owner) { shelf ->
                shelf?.let {
                    it.books?.let {
                        mView?.showBookListOnShelf(it)
                    }
                    mView?.showShelfList(shelf)
                }
            }
        }
    }

    override fun onTapBookOption(book: BookVO) {
        mView?.showBottomSheet(book)
    }

    override fun onTapBook(book: BookVO) {
        mView?.navigateToBookDetail(book)
    }

    override fun onTapRenameShelf(newName: String, owner: LifecycleOwner) {
        mShelf?.title?.let {
            mShelfModel.renameShelf(newName, it)
        }
        mShelfModel.getShelf(newName)?.observe(owner) {
            if (it != null) {
//                mView?.showRenamedShelf(it)
                mView?.showShelfList(it)
            }
        }
    }

    override fun onTapDeleteShelf() {
        mShelf?.let {
            mShelfModel.deleteShelf(it)
        }
    }

    override fun getExtraShelf(shelf: ShelfVO) {
        mShelf = shelf
    }

    override fun onTapAddToShelf(bookJson: String) {
        mView?.navigateToAddToShelfScreen(bookJson)
    }
}