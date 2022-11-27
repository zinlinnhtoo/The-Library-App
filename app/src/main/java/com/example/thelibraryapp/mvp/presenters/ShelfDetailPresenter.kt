package com.example.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.mvp.views.ShelfDetailView

interface ShelfDetailPresenter :
    IBasePresenter,
    BookOptionDelegate,
    BookViewHolderDelegate {
        fun initView(view: ShelfDetailView)
        fun onTapRenameShelf(newName: String, owner: LifecycleOwner)
        fun onTapDeleteShelf()
        fun getExtraShelf(shelf: ShelfVO)
        fun onTapAddToShelf(bookJson: String)
}