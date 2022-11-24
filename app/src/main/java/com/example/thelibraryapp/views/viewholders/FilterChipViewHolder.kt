package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.CategoryVO
import com.example.thelibraryapp.delegates.FilterChipDelegate
import kotlinx.android.synthetic.main.view_holder_filter_chip.view.*

class FilterChipViewHolder(
    itemView: View,
    private val mChipDelegate: FilterChipDelegate
) : ViewHolder(itemView) {

    private var mCategory: CategoryVO? = null

    init {
        itemView.setOnClickListener {
            mCategory?.let {
                mChipDelegate.onTapChip(it)
            }
        }
    }

    fun bindData(category: CategoryVO) {
        mCategory = category
        itemView.categoryChip.text = category.category

        if (category.isChecked) {
            itemView.categoryChip.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            itemView.categoryChip.setBackgroundResource(R.drawable.background_selected_chip)
        } else {
            itemView.categoryChip.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
            itemView.categoryChip.setBackgroundResource(R.drawable.background_chip)
        }
    }
}