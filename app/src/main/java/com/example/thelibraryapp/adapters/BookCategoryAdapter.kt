package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.GoToCategoryDelegate
import com.example.thelibraryapp.views.viewholders.BookCategoryViewHolder

class BookCategoryAdapter(
    private val mBookOptionDelegate: BookOptionDelegate,
    private val mBookCategoryDelegate: GoToCategoryDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
): Adapter<BookCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_book_category, parent, false)
        return BookCategoryViewHolder(view, mBookCategoryDelegate)
    }

    override fun onBindViewHolder(holder: BookCategoryViewHolder, position: Int) {
        holder.setUpBookListRecyclerView(mBookOptionDelegate, mBookViewHolderDelegate)
    }

    override fun getItemCount(): Int {
        return 3
    }
}