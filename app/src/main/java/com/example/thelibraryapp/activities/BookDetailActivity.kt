package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.ReviewAdapter
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.mvp.presenters.BookDetailPresenter
import com.example.thelibraryapp.mvp.presenters.BookDetailPresenterImpl
import com.example.thelibraryapp.mvp.views.BookDetailView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity(), BookDetailView {

    //intent extra from HomeActivity
    private var mBookJson: String? = null
    private lateinit var mReviewAdapter: ReviewAdapter

    private lateinit var mPresenter: BookDetailPresenter

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

        setUpPresenter()

        getExtraFromHomeActivity()

        setUpReviewRecyclerView()

        setUpListener()

        mPresenter.onUiReady(this)

    }

    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[BookDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun bindData(book: BookVO) {
        tvBookTitleDetail.text = book.title
        tvAuthor.text = book.author
        tvBookCategory.text = book.bookCategory
        Glide.with(this)
            .load(book.bookImage)
            .into(ivBookDetail)
        tvBookDetail.text = book.description
    }

    private fun setUpReviewRecyclerView() {
        mReviewAdapter = ReviewAdapter()
        rvReview.adapter = mReviewAdapter
        rvReview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getExtraFromHomeActivity() {
        mBookJson = intent?.getStringExtra(EXTRA_BOOK_TITLE)

        mBookJson?.let {
            mPresenter.getExtraBook(Gson().fromJson(it, BookVO::class.java))
        }
    }

    override fun showBookDetail(book: BookVO) {
        bindData(book)
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }
}