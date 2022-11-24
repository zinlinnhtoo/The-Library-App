package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.BookSearchAdapter
import kotlinx.android.synthetic.main.activity_book_search.*

class BookSearchActivity : AppCompatActivity() {

    private lateinit var mBookSearchAdapter: BookSearchAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BookSearchActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)

        mBookSearchAdapter = BookSearchAdapter()
        rvSearchBook.adapter = mBookSearchAdapter
        rvSearchBook.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}