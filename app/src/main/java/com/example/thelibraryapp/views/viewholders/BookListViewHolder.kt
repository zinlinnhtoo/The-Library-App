package com.example.thelibraryapp.views.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_book_list.view.*

class BookListViewHolder(
    itemView: View,
    private val mOptionDelegate: BookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
    ) : ViewHolder(itemView) {

    private var mBook: BookVO? = null

    init {

        itemView.setOnClickListener {
            mBookViewHolderDelegate.onTapBook()
        }

        itemView.ivOption.setOnClickListener {
            mOptionDelegate.onTapBookOption()
        }
    }

    fun bindData(book: BookVO) {
        mBook = book

        itemView.tvBookTitle.text = book.title

        Glide.with(itemView.context)
            .load("${book.bookImage}")
            .into(itemView.ivBook)

//        val params: ViewGroup.LayoutParams = itemView.flBook.layoutParams
//        params.width = book.bookWidth!!
//        params.height = book.bookHeight!!
//
//        itemView.flBook.layoutParams = params
    }
}