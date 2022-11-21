package com.example.thelibraryapp.views.viewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.delegates.AddToShelfCheckboxDelegate
import kotlinx.android.synthetic.main.bottomsheet_book_option.*
import kotlinx.android.synthetic.main.view_holder_add_to_shelves.view.*

class AddToShelvesViewHolder(
    itemView: View,
    private val mCheckboxDelegate: AddToShelfCheckboxDelegate
) : ViewHolder(itemView) {

    private var mShelf: ShelfVO? = null

    init {
        itemView.cbAddToShelf.setOnCheckedChangeListener { _, isChecked ->
            mShelf?.let {
                mCheckboxDelegate.onCheckedCheckbox(it, isChecked)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun bindData(shelf: ShelfVO) {
        mShelf  = shelf

        itemView.tvShelfTitle.text = shelf.title
        itemView.tvBookCount.text = "${shelf.books?.count()} books"
//        Glide.with(itemView.context)
//            .load("${shelf.books?.first()?.bookImage}")
//            .into(itemView.ivShelf)
    }
}