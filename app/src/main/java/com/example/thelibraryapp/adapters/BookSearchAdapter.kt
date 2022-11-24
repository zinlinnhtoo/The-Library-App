package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.views.viewholders.BookSearchViewHolder

class BookSearchAdapter: Adapter<BookSearchViewHolder>() {

    private var mBookList: List<BookVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_search_book, parent, false)
        return BookSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        if (mBookList.isNotEmpty()) {
            holder.bindData(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        return mBookList.count()
    }

    fun setNewData(bookList: List<BookVO>) {
        mBookList = bookList
        notifyDataSetChanged()
    }
}