package com.example.thelibraryapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.FilterChipDelegate
import com.example.thelibraryapp.views.viewpods.YourBooksViewPod
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_shelf_detail.*
import kotlinx.android.synthetic.main.bottomsheet_book_option.*

class ShelfDetailActivity : AppCompatActivity(), BookOptionDelegate, BookViewHolderDelegate, FilterChipDelegate {

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

        tvShelfName.text = mShelf?.title
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
        }

        ivBtnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPod() {
        mYourBooksViewPod = vpYourBooks as YourBooksViewPod
        mYourBooksViewPod.setUpYourBooksViewPod(this, this, this)
    }

    override fun onTapBookOption() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottomsheet_book_option)
        dialog.show()
        dialog.llAddToShelves.setOnClickListener {
            dialog.dismiss()
            startActivity(AddToShelvesActivity.newIntent(this))
            overridePendingTransition(0, 0)
        }
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