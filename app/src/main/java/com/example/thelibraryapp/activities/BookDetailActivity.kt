package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.models.BookModel
import com.example.thelibraryapp.data.models.BookModelImpl
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BookDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
    }
}