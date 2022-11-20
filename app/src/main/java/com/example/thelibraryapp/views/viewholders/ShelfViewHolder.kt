package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.delegates.ShelfViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_shelf.view.*

class ShelfViewHolder(
    itemView: View,
    private val mShelfViewHolderDelegate: ShelfViewHolderDelegate
    ) : ViewHolder(itemView) {

    private var mShelf: ShelfVO? = null

        init {
            itemView.setOnClickListener {
                mShelf?.let {
                    mShelfViewHolderDelegate.onTapShelf()
                }
            }
        }

    fun bindData(shelf: ShelfVO) {
        mShelf = shelf

        itemView.tvShelfTitle.text = shelf.title
    }
}