package com.example.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.delegates.ShelfViewHolderDelegate
import com.example.thelibraryapp.views.viewholders.ShelfViewHolder

class ShelfAdapter(
    private val mShelfViewHolderDelegate: ShelfViewHolderDelegate
): Adapter<ShelfViewHolder>() {

    private var mShelfList: List<ShelfVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelfViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_shelf, parent, false)
        return ShelfViewHolder(view, mShelfViewHolderDelegate)
    }

    override fun onBindViewHolder(holder: ShelfViewHolder, position: Int) {
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