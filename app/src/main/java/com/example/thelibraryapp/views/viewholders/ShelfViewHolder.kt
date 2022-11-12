package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.delegates.ShelfViewHolderDelegate

class ShelfViewHolder(
    itemView: View,
    private val mShelfViewHolderDelegate: ShelfViewHolderDelegate
    ) : ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                mShelfViewHolderDelegate.onTapShelf()
            }
        }
}