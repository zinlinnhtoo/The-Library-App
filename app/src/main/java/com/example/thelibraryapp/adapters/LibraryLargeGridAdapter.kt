package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.views.viewholders.LibraryLargeGridBookViewHolder
import com.example.thelibraryapp.views.viewholders.LibraryListBookViewHolder

class LibraryLargeGridAdapter(
    private val mOptionDelegate: BookOptionDelegate
): Adapter<LibraryLargeGridBookViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LibraryLargeGridBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_library_large_grid_book, parent, false)
        return LibraryLargeGridBookViewHolder(view, mOptionDelegate)
    }

    override fun onBindViewHolder(holder: LibraryLargeGridBookViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 20
    }
}