package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.adapters.BookListAdapter
import kotlinx.android.synthetic.main.view_holder_book_category.view.*

class BookCategoryViewHolder(itemView: View) : ViewHolder(itemView) {
    lateinit var mBookListAdapter: BookListAdapter
    var rvBookList: RecyclerView = itemView.rvBookList

    fun setUpBookListRecyclerView() {
        mBookListAdapter = BookListAdapter()
        rvBookList.adapter = mBookListAdapter
        rvBookList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
    }
}