package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.CategoryBookListAdapter
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.mvp.presenters.BookCategoryPresenter
import com.example.thelibraryapp.mvp.presenters.BookCategoryPresenterImpl
import com.example.thelibraryapp.mvp.views.BookCategoryView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_book_category.*

class BookCategoryActivity : AppCompatActivity(), BookCategoryView{

    private lateinit var mCategoryBookListAdapter: CategoryBookListAdapter

    private lateinit var mPresenter: BookCategoryPresenter

    companion object {

        private const val EXTRA_CATEGORY_NAME = "EXTRA_CATEGORY_NAME"

        fun newIntent(context: Context, categoryTitle: String): Intent {
            return Intent(context, BookCategoryActivity::class.java)
                .putExtra(EXTRA_CATEGORY_NAME, categoryTitle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_category)

        setUpPresenter()

        setUpRecyclerView()

        setUpClickListener()

        intent?.getStringExtra(EXTRA_CATEGORY_NAME)?.let { mPresenter.getExtraCategoryString(it) }

        mPresenter.onUiReady(this)

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[BookCategoryPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpClickListener() {
        ivBtnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpRecyclerView() {
        mCategoryBookListAdapter = CategoryBookListAdapter(mPresenter, mPresenter)
        rvCategoryBookList.adapter = mCategoryBookListAdapter
        rvCategoryBookList.layoutManager = GridLayoutManager(this, 2)
    }

    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottomsheet_category_book_option)
        dialog.show()
    }

    override fun showBookList(bookList: List<BookVO>) {
        mCategoryBookListAdapter.setNewData(bookList)
    }

    override fun showCategoryBottomSheet() {
        showBottomSheetDialog()
    }

    override fun navigateToBookDetail(book: BookVO) {
        val bookJson = Gson().toJson(book)
        startActivity(BookDetailActivity.newIntent(this, bookJson))
    }

    override fun setUpCategoryTitle(categoryTitle: String) {
        tvTitleInCategory.text = categoryTitle
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }
}