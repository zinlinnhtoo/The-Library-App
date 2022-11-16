package com.example.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.OverviewListVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.GoToCategoryDelegate
import com.example.thelibraryapp.views.viewholders.BookCategoryViewHolder

class BookCategoryAdapter(
    private val mBookOptionDelegate: BookOptionDelegate,
    private val mBookCategoryDelegate: GoToCategoryDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
): Adapter<BookCategoryViewHolder>() {

    private var mOverviewList: List<OverviewListVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_book_category, parent, false)
        return BookCategoryViewHolder(view, mBookCategoryDelegate)
    }

    override fun onBindViewHolder(holder: BookCategoryViewHolder, position: Int) {
        if (mOverviewList.isNotEmpty()) {
            holder.bindData(mOverviewList[position])
            mOverviewList[position].books?.let { holder.setUpBookListRecyclerView(it, mBookOptionDelegate, mBookViewHolderDelegate) }
        }


    }

    override fun getItemCount(): Int {
        return mOverviewList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(overviewListVO: List<OverviewListVO>) {
        mOverviewList = overviewListVO
        notifyDataSetChanged()
    }
}