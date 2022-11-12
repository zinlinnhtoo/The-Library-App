package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.delegates.BookViewHolderDelegate

class BannerBookViewHolder(
    itemView: View,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
) : ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            mBookViewHolderDelegate.onTapBook()
        }
    }
}