package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.views.viewholders.BookCategoryViewHolder

class BookCategoryAdapter: Adapter<BookCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_book_category, parent, false)
        return BookCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookCategoryViewHolder, position: Int) {
        holder.setUpBookListRecyclerView()
    }

    override fun getItemCount(): Int {
        return 3
    }
}