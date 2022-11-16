package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_library_list_book.view.*

class LibraryListBookViewHolder(
    itemView: View,
    private val mOptionDelegate: BookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
    ) : ViewHolder(itemView) {

    init {

        itemView.setOnClickListener {
            mBookViewHolderDelegate.onTapBook("Library List")
        }

        itemView.ivOption.setOnClickListener {
            mOptionDelegate.onTapBookOption()
        }
    }
}