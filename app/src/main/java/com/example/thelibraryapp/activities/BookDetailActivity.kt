package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thelibraryapp.R
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    //intent extra from HomeActivity
    private var mBookTitle: String? = null


    companion object {

        private const val EXTRA_BOOK_TITLE = "EXTRA_BOOK_TITLE"

        fun newIntent(context: Context, bookTitle: String): Intent {
            return Intent(context, BookDetailActivity::class.java)
                .putExtra(EXTRA_BOOK_TITLE, bookTitle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        getExtraFromHomeActivity()

        tvBookTitleDetail.text = mBookTitle
    }

    private fun getExtraFromHomeActivity() {
        mBookTitle = intent?.getStringExtra(EXTRA_BOOK_TITLE)
    }
}