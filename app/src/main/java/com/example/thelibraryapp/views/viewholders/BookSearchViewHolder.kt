package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_search_book.view.*

class BookSearchViewHolder(itemView: View, private val mBookViewHolderDelegate: BookViewHolderDelegate) : ViewHolder(itemView) {

    private var mBook: BookVO? = null

    init {
        itemView.setOnClickListener {
            mBook?.let {
                mBookViewHolderDelegate.onTapBook(it)
            }
        }
    }


    fun bindData(book: BookVO) {
        mBook = book
        itemView.tvBookTitle.text = book.title

        Glide.with(itemView.context)
            .load(book.bookImage)
            .into(itemView.ivBook)

        itemView.tvAuthor.text = book.author
        itemView.tvBookType.text = book.bookCategory
    }
}