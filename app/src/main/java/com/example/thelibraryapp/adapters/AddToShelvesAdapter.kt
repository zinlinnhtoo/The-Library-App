package com.example.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.views.viewholders.AddToShelvesViewHolder

class AddToShelvesAdapter: Adapter<AddToShelvesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToShelvesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_add_to_shelves, parent, false)
        return AddToShelvesViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddToShelvesViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}