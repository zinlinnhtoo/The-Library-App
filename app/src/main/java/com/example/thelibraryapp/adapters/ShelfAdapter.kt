package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.delegates.ShelfViewHolderDelegate
import com.example.thelibraryapp.views.viewholders.ShelfViewHolder

class ShelfAdapter(
    private val mShelfViewHolderDelegate: ShelfViewHolderDelegate
): Adapter<ShelfViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelfViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_shelf, parent, false)
        return ShelfViewHolder(view, mShelfViewHolderDelegate)
    }

    override fun onBindViewHolder(holder: ShelfViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 3
    }
}