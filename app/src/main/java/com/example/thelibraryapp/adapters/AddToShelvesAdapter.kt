package com.example.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.delegates.AddToShelfCheckboxDelegate
import com.example.thelibraryapp.views.viewholders.AddToShelvesViewHolder

class AddToShelvesAdapter(
    private val mCheckboxDelegate: AddToShelfCheckboxDelegate
): Adapter<AddToShelvesViewHolder>() {

    private var mShelfList: List<ShelfVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToShelvesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_add_to_shelves, parent, false)
        return AddToShelvesViewHolder(view, mCheckboxDelegate)
    }

    override fun onBindViewHolder(holder: AddToShelvesViewHolder, position: Int) {
        if (mShelfList.isNotEmpty()) {
            holder.bindData(mShelfList[position])
        }
    }

    override fun getItemCount(): Int {
        return mShelfList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(shelfList: List<ShelfVO>) {
        mShelfList = shelfList
        notifyDataSetChanged()
    }
}