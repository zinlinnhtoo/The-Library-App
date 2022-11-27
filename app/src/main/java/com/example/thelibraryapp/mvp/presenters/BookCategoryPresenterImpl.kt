package com.example.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.thelibraryapp.data.models.BookModel
import com.example.thelibraryapp.data.models.BookModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.mvp.views.BookCategoryView

class BookCategoryPresenterImpl :
    ViewModel(),
    BookCategoryPresenter {

    private val mBookModel: BookModel = BookModelImpl

    private val mBookList: MutableList<BookVO> = mutableListOf()

    private var mCategoryTitle: String? = null

    private var mView: BookCategoryView? = null

    override fun onTapBook(book: BookVO) {
        mView?.navigateToBookDetail(book)
    }

    override fun onTapOptionFromCategory() {
        mView?.showCategoryBottomSheet()
    }

    override fun initView(view: BookCategoryView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mCategoryTitle?.let {
            mBookModel.getBookListByListName(it,
            onSuccess = {
                it.forEach {
                    it.bookDetailList?.forEach {
                        mBookList.add(it)
                    }
                }
                mView?.showBookList(mBookList)
            },
            onFailure = {
                mView?.showError(it)
            })
        }
        mCategoryTitle?.let { mView?.setUpCategoryTitle(it) }
    }

    override fun getExtraCategoryString(categoryTitle: String) {
        mCategoryTitle = categoryTitle
    }
}