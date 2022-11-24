package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.CategoryBookOptionDelegate
import com.example.thelibraryapp.views.viewholders.CategoryBookListViewHolder

class CategoryBookListAdapter(
    private val mCategoryBookOptionDelegate: CategoryBookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
): Adapter<CategoryBookListViewHolder>() {

    private var mBookList: List<BookVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryBookListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_category_book_list, parent, false)
        return CategoryBookListViewHolder(view, mCategoryBookOptionDelegate, mBookViewHolderDelegate)
    }

    override fun onBindViewHolder(holder: CategoryBookListViewHolder, position: Int) {
        if (mBookList.isNotEmpty()) {
            holder.bindData(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        return mBookList.count()
    }

    fun setNewData(bookList: List<BookVO>) {
        mBookList = bookList
        notifyDataSetChanged()
    }

}