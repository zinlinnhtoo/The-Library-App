package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.adapters.BookListAdapter
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.OverviewListVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.GoToCategoryDelegate
import kotlinx.android.synthetic.main.view_holder_book_category.view.*

class BookCategoryViewHolder(
    itemView: View,
    private val mCategoryDelegate: GoToCategoryDelegate
    ) : ViewHolder(itemView) {

    lateinit var mBookListAdapter: BookListAdapter
    var rvBookList: RecyclerView = itemView.rvBookList

    private var mOverviewList: OverviewListVO? = null

    init {
        itemView.llCategoryTitle.setOnClickListener {
            mOverviewList?.listName?.let {
                mCategoryDelegate.onTapCategory(it)
            }
        }
    }

    fun setUpBookListRecyclerView(
        bookList: List<BookVO>,
        bookOptionDelegate: BookOptionDelegate,
        bookViewHolderDelegate: BookViewHolderDelegate
    ) {
        mBookListAdapter = BookListAdapter(bookOptionDelegate, bookViewHolderDelegate)
        rvBookList.adapter = mBookListAdapter
        rvBookList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        mOverviewList?.let {
            mBookListAdapter.setNewData(bookList)
        }
    }

    fun bindData(overviewList: OverviewListVO) {
        mOverviewList = overviewList
        itemView.tvCategoryTitle.text = overviewList.listName
    }
}