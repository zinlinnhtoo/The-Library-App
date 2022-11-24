package com.example.thelibraryapp.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.CategoryBookOptionDelegate
import kotlinx.android.synthetic.main.view_holder_category_book_list.view.*

class CategoryBookListViewHolder(
    itemView: View,
    private val mCategoryBookOptionDelegate: CategoryBookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
    ) : ViewHolder(itemView) {

    private var mBook: BookVO? = null

    init {

        itemView.setOnClickListener {
            mBook?.let {
                mBookViewHolderDelegate.onTapBook(it)
            }
        }

        itemView.ivOptionInCategory.setOnClickListener {
            mCategoryBookOptionDelegate.onTapOptionFromCategory()
        }
    }

    fun bindData(book: BookVO) {
        mBook = book

        itemView.tvBookTitleInCategory.text = book.title
    }
}