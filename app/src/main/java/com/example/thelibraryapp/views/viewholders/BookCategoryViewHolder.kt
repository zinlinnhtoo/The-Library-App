package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.adapters.BookListAdapter
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ListVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.GoToCategoryDelegate
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.view_holder_book_category.view.*

class BookCategoryViewHolder(
    itemView: View,
    private val mCategoryDelegate: GoToCategoryDelegate
    ) : ViewHolder(itemView) {

    lateinit var mBookListAdapter: BookListAdapter
    var rvBookList: RecyclerView = itemView.rvBookList

    private var mList: ListVO? = null

    init {
        itemView.llCategoryTitle.setOnClickListener {
            mCategoryDelegate.onTapCategory()
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
        mList?.let {
            mBookListAdapter.setNewData(bookList)
        }
    }

    fun bindData(list: ListVO) {
        mList = list
        itemView.tvCategoryTitle.text = list.listName
    }
}