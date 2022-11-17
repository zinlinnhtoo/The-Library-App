package com.example.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.views.viewholders.BannerBookViewHolder

class BannerBookAdapter(
    private val mBookViewHolderDelegate: BookViewHolderDelegate
): Adapter<BannerBookViewHolder>() {

    private var mBookList: List<BookVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_banner_book, parent, false)
        return BannerBookViewHolder(view, mBookViewHolderDelegate)
    }

    override fun onBindViewHolder(holder: BannerBookViewHolder, position: Int) {
        if (mBookList.isNotEmpty()) {
            holder.bindData(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        return mBookList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(bookList: List<BookVO>) {
        mBookList = bookList
        notifyDataSetChanged()
    }
}