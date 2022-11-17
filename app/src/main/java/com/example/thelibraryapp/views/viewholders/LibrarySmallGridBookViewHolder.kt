package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_library_small_grid_book.view.*

class LibrarySmallGridBookViewHolder(
    itemView: View,
    private val mOptionDelegate: BookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    init {

        itemView.setOnClickListener {
//            mBookViewHolderDelegate.onTapBook()
        }

        itemView.ivOptionSmallGrid.setOnClickListener {
            mOptionDelegate.onTapBookOption()
        }
    }
}