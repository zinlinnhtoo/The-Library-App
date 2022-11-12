package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.views.viewholders.LibrarySmallGridBookViewHolder

class LibrarySmallGridBookAdapter(
    private val mOptionDelegate: BookOptionDelegate,
    private val mBookViewHolderDelegate: BookViewHolderDelegate
): Adapter<LibrarySmallGridBookViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LibrarySmallGridBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_library_small_grid_book, parent, false)
        return LibrarySmallGridBookViewHolder(view, mOptionDelegate, mBookViewHolderDelegate)
    }

    override fun onBindViewHolder(holder: LibrarySmallGridBookViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 25
    }
}