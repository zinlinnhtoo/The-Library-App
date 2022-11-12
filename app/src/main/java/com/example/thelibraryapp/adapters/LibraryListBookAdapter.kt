package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.views.viewholders.LibraryListBookViewHolder

class LibraryListBookAdapter(
    private val mOptionDelegate: BookOptionDelegate
): Adapter<LibraryListBookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryListBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_library_list_book, parent, false)
        return LibraryListBookViewHolder(view, mOptionDelegate)
    }

    override fun onBindViewHolder(holder: LibraryListBookViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 20
    }
}