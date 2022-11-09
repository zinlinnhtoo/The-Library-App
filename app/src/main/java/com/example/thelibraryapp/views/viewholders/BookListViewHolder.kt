package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.delegates.BookOptionDelegate
import kotlinx.android.synthetic.main.view_holder_book_list.view.*

class BookListViewHolder(itemView: View, private val mOptionDelegate: BookOptionDelegate) : ViewHolder(itemView) {

    init {
        itemView.ivOption.setOnClickListener {
            mOptionDelegate.onTapBookOption()
        }
    }
}