package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.BookVO
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    private var mBook: BookVO? = null

    //intent extra from HomeActivity
    private var mBookJson: String? = null



    companion object {

        private const val EXTRA_BOOK_TITLE = "EXTRA_BOOK_TITLE"

        fun newIntent(context: Context, bookJson: String): Intent {
            return Intent(context, BookDetailActivity::class.java)
                .putExtra(EXTRA_BOOK_TITLE, bookJson)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        getExtraFromHomeActivity()

        tvBookTitleDetail.text = mBook?.title

    }

    private fun getExtraFromHomeActivity() {
        mBookJson = intent?.getStringExtra(EXTRA_BOOK_TITLE)

        mBookJson?.let {
            mBook = Gson().fromJson(it, BookVO::class.java)
        }
    }
}