package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_banner_book.view.*

class BannerBookViewHolder(
    itemView: View,
    private val mBookViewHolderDelegate: BookViewHolderDelegate,
    private val mBookOptionDelegate: BookOptionDelegate
) : ViewHolder(itemView) {

    private var mBook: BookVO? = null

    init {
        itemView.setOnClickListener {
            mBook?.let {
                mBookViewHolderDelegate.onTapBook(it)
            }
        }
        itemView.setOnClickListener {
            mBook?.let {
                mBookOptionDelegate.onTapBookOption(it)
            }
        }
    }

    fun bindData(book: BookVO) {
        mBook = book

        Glide.with(itemView.context)
            .load(book.bookImage)
            .into(itemView.ivBook)
    }
}