package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.views.viewholders.BannerBookViewHolder

class BannerBookAdapter: Adapter<BannerBookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_banner_book, parent, false)
        return BannerBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerBookViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}