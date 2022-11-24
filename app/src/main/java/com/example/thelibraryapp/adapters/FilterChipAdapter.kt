package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.CategoryVO
import com.example.thelibraryapp.delegates.FilterChipDelegate
import com.example.thelibraryapp.views.viewholders.FilterChipViewHolder

class FilterChipAdapter(
    private val mChipDelegate: FilterChipDelegate
): Adapter<FilterChipViewHolder>() {

    private var mCategoryList: List<CategoryVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterChipViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_filter_chip, parent, false)
        return FilterChipViewHolder(view, mChipDelegate)
    }

    override fun onBindViewHolder(holder: FilterChipViewHolder, position: Int) {
        if (mCategoryList.isNotEmpty()) {
            holder.bindData(mCategoryList[position])
        }
    }

    override fun getItemCount(): Int {
        return mCategoryList.count()
    }

    fun setNewData(categoryList: List<CategoryVO>) {
        mCategoryList = categoryList
        notifyDataSetChanged()
    }
}