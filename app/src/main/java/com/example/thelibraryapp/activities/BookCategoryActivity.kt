package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.CategoryBookListAdapter
import com.example.thelibraryapp.delegates.CategoryBookOptionDelegate
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_book_category.*
import kotlinx.android.synthetic.main.activity_home.*

class BookCategoryActivity : AppCompatActivity(), CategoryBookOptionDelegate {

    private lateinit var mCategoryBookListAdapter: CategoryBookListAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BookCategoryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_category)

        setUpRecyclerView()

        setUpClickListener()
    }

    private fun setUpClickListener() {
        ivBtnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpRecyclerView() {
        mCategoryBookListAdapter = CategoryBookListAdapter(this)
        rvCategoryBookList.adapter = mCategoryBookListAdapter
        rvCategoryBookList.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onTapOptionFromCategory() {
        showBottomSheetDialog()
    }

    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottomsheet_book_option)
        dialog.show()
    }
}