package com.example.thelibraryapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.models.ShelfModel
import com.example.thelibraryapp.data.models.ShelfModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.FilterChipDelegate
import com.example.thelibraryapp.views.viewpods.YourBooksViewPod
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_shelf_detail.*
import kotlinx.android.synthetic.main.activity_shelf_detail.tvShelfName
import kotlinx.android.synthetic.main.bottomsheet_book_option.*
import kotlinx.android.synthetic.main.bottomsheet_shelf_detail.*

class ShelfDetailActivity : AppCompatActivity(), BookOptionDelegate, BookViewHolderDelegate, FilterChipDelegate {

    private val mShelfModel: ShelfModel = ShelfModelImpl

    lateinit var mYourBooksViewPod: YourBooksViewPod

    private var mShelf: ShelfVO? = null
    private var mShelfJson: String? = null

    companion object {
        private const val EXTRA_SHELF = "EXTRA_SHELF"

        fun newIntent(context: Context, shelf: String): Intent {
            return Intent(context, ShelfDetailActivity::class.java)
                .putExtra(EXTRA_SHELF, shelf)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelf_detail)

        setUpViewPod()
        
        setUpListener()

        getExtra()

        bindData()

        etShelfName.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                val newShelf = ShelfVO(
                    title = etShelfName.text.toString(),
                    books = mShelf?.books
                )
                mShelf?.let { mShelfModel.deleteShelf(it) }
                mShelfModel.insertShelf(newShelf)
                super.onBackPressed()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindData() {
        tvShelfName.text = mShelf?.title
        etShelfName.setText(mShelf?.title)
        etShelfName.requestFocus()
        etShelfName.isCursorVisible = true
        tvBookCount.text = mShelf?.books?.count().toString() + " books"
    }

    private fun getExtra() {
        mShelfJson = intent?.getStringExtra(EXTRA_SHELF)

        mShelfJson?.let {
            mShelf = Gson().fromJson(it, ShelfVO::class.java)
        }
    }

    private fun setUpListener() {
        ivBtnOption.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.bottomsheet_shelf_detail)
            dialog.show()

            dialog.tvRenameShelf.setOnClickListener {
                dialog.dismiss()

                if (tvShelfName.visibility == View.VISIBLE) {
                    tvShelfName.visibility = View.GONE
                    etShelfName.visibility = View.VISIBLE
                } else {
                    tvShelfName.visibility = View.VISIBLE
                    etShelfName.visibility = View.GONE
                }

            }
            dialog.tvDeleteShelf.setOnClickListener {
                mShelf?.let { it1 -> mShelfModel.deleteShelf(it1) }
                dialog.dismiss()
                super.onBackPressed()
            }
        }

        ivBtnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPod() {
        mYourBooksViewPod = vpYourBooks as YourBooksViewPod
        mYourBooksViewPod.setUpYourBooksViewPod(this, this, this)
    }

    override fun onTapBookOption(book: BookVO) {
        val bookJson = Gson().toJson(book)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottomsheet_book_option)
        dialog.show()
        dialog.llAddToShelves.setOnClickListener {
            dialog.dismiss()
            startActivity(AddToShelvesActivity.newIntent(this, bookJson))
            overridePendingTransition(0, 0)
        }
        dialog.tvBottomSheetBookTitle.text = book.title
        dialog.tvBottomSheetBookAuthor.text = book.author
        Glide.with(this)
            .load("${book.bookImage}")
            .into(dialog.ivBottomSheetBook)
    }

    override fun onTapBook(book: BookVO) {
        val bookJson = Gson().toJson(book)
        startActivity(BookDetailActivity.newIntent(this, bookJson))
        overridePendingTransition(0, 0)
    }

    override fun tapChip() {
        Toast.makeText(this, "Tap Chip", Toast.LENGTH_SHORT).show()
    }
}