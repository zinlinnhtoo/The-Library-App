package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.views.viewholders.BookListViewHolder

class BookListAdapter(
    private val mOptionDelegate: BookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
    ): Adapter<BookListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_book_list, parent, false)
        return BookListViewHolder(view, mOptionDelegate, mBookViewHolderDelegate)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}