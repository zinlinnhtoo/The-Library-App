package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.CategoryBookListAdapter
import kotlinx.android.synthetic.main.activity_book_category.*

class BookCategoryActivity : AppCompatActivity() {

    private lateinit var mCategoryBookListAdapter: CategoryBookListAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BookCategoryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_category)

        mCategoryBookListAdapter = CategoryBookListAdapter()
        rvCategoryBookList.adapter = mCategoryBookListAdapter
        rvCategoryBookList.layoutManager = GridLayoutManager(this, 2,)
    }
}