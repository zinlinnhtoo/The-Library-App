package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.CategoryBookOptionDelegate
import kotlinx.android.synthetic.main.view_holder_category_book_list.view.*

class CategoryBookListViewHolder(
    itemView: View,
    private val mCategoryBookOptionDelegate: CategoryBookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
    ) : ViewHolder(itemView) {

    init {

        itemView.setOnClickListener {
            mBookViewHolderDelegate.onTapBook()
        }

        itemView.ivOptionInCategory.setOnClickListener {
            mCategoryBookOptionDelegate.onTapOptionFromCategory()
        }
    }
}