package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.delegates.FilterChipDelegate

class FilterChipViewHolder(
    itemView: View,
    private val mChipDelegate: FilterChipDelegate
) : ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            mChipDelegate.tapChip()
        }
    }
}