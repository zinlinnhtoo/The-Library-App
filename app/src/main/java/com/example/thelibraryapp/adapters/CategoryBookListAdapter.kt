package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.CategoryBookOptionDelegate
import com.example.thelibraryapp.views.viewholders.CategoryBookListViewHolder

class CategoryBookListAdapter(
    private val mCategoryBookOptionDelegate: CategoryBookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
): Adapter<CategoryBookListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryBookListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_category_book_list, parent, false)
        return CategoryBookListViewHolder(view, mCategoryBookOptionDelegate, mBookViewHolderDelegate)
    }

    override fun onBindViewHolder(holder: CategoryBookListViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 15
    }

}