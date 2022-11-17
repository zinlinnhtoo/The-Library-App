package com.example.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.views.viewholders.LibraryLargeGridBookViewHolder

class LibraryLargeGridBookAdapter(
    private val mOptionDelegate: BookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
): Adapter<LibraryLargeGridBookViewHolder>() {

    private var mBookList: List<BookVO> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LibraryLargeGridBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_library_large_grid_book, parent, false)
        return LibraryLargeGridBookViewHolder(view, mOptionDelegate, mBookViewHolderDelegate)
    }

    override fun onBindViewHolder(holder: LibraryLargeGridBookViewHolder, position: Int) {
        if (mBookList.isNotEmpty()) {
            holder.bindData(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        return mBookList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(book: List<BookVO>) {
        mBookList = book
        notifyDataSetChanged()
    }
}