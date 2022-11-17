package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.views.viewpods.YourBooksViewPod
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_shelf_detail.*
import kotlinx.android.synthetic.main.bottomsheet_book_option.*

class ShelfDetailActivity : AppCompatActivity(), BookOptionDelegate, BookViewHolderDelegate {

    lateinit var mYourBooksViewPod: YourBooksViewPod

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ShelfDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelf_detail)

        setUpViewPod()
        
        setUpListener()
    }

    private fun setUpListener() {
        ivBtnOption.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.bottomsheet_shelf_detail)
            dialog.show()
        }
    }

    private fun setUpViewPod() {
        mYourBooksViewPod = vpYourBooks as YourBooksViewPod
        mYourBooksViewPod.setUpYourBooksViewPod(this, this)
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
}