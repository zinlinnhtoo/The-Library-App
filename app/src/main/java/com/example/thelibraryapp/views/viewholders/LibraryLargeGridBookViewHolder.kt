package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_library_large_grid_book.view.*

class LibraryLargeGridBookViewHolder(
    itemView: View,
    private val mOptionDelegate: BookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
) : ViewHolder(itemView) {

    private var mBook: BookVO? = null

    init {

        itemView.setOnClickListener {
            mBook?.let {
                mBookViewHolderDelegate.onTapBook(it)
            }
        }

        itemView.ivOptionLargeGrid.setOnClickListener {
            mBook?.let {
                mOptionDelegate.onTapBookOption(it)
            }
        }
    }

    fun bindData(book: BookVO) {
        mBook = book

        Glide.with(itemView.context)
            .load(book.bookImage)
            .into(itemView.ivBookLargeGrid)

        itemView.tvBookTitleLargeGrid.text = book.title
    }

}