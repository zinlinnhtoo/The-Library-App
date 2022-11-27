package com.example.thelibraryapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.mvp.presenters.ShelfDetailPresenter
import com.example.thelibraryapp.mvp.presenters.ShelfDetailPresenterImpl
import com.example.thelibraryapp.mvp.views.ShelfDetailView
import com.example.thelibraryapp.views.viewpods.YourBooksViewPod
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_shelf_detail.*
import kotlinx.android.synthetic.main.activity_shelf_detail.tvShelfName
import kotlinx.android.synthetic.main.bottomsheet_book_option.*
import kotlinx.android.synthetic.main.bottomsheet_shelf_detail.*

class ShelfDetailActivity : AppCompatActivity(), ShelfDetailView {

    lateinit var mYourBooksViewPod: YourBooksViewPod

    private var mShelfJson: String? = null
    private lateinit var mPresenter: ShelfDetailPresenter

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
        setUpPresenter()

        setUpViewPod()
        
        setUpListener()

        getExtra()

        renameShelf()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[ShelfDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun renameShelf() {
        etShelfName.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                val newShelfTitle = etShelfName.text.toString()
                mPresenter.onTapRenameShelf(newShelfTitle, this)
                super.onBackPressed()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindData(shelf: ShelfVO?) {
        tvShelfName.text = shelf?.title
        etShelfName.setText(shelf?.title)
        etShelfName.setText("")
        etShelfName.requestFocus()
        etShelfName.isCursorVisible = true
        tvBookCount.text = shelf?.books?.count().toString() + " books"
    }

    private fun getExtra() {
        mShelfJson = intent?.getStringExtra(EXTRA_SHELF)

        mShelfJson?.let {
            mPresenter.getExtraShelf(Gson().fromJson(it, ShelfVO::class.java))
        }
    }

    private fun setUpListener() {
        ivBtnOption.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.bottomsheet_shelf_detail)
            dialog.show()

            dialog.tvRenameShelf.setOnClickListener {
                etShelfName.visibility = View.VISIBLE
                dialog.dismiss()
                etShelfName.requestFocus()

                if (tvShelfName.visibility == View.VISIBLE) {
                    tvShelfName.visibility = View.GONE
                    etShelfName.visibility = View.VISIBLE
                } else {
                    tvShelfName.visibility = View.VISIBLE
                    etShelfName.visibility = View.GONE
                }

            }
            dialog.tvDeleteShelf.setOnClickListener {
                mPresenter.onTapDeleteShelf()
                finish()
                dialog.dismiss()
            }
        }

        ivBtnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPod() {
        mYourBooksViewPod = vpYourBooks as YourBooksViewPod
        mYourBooksViewPod.setUpYourBooksViewPod(mPresenter, mPresenter)
    }

    override fun showBookListOnShelf(bookList: List<BookVO>) {
        mYourBooksViewPod.setData(bookList)
    }

    override fun showShelfList(shelf: ShelfVO) {
        bindData(shelf)
    }

    override fun showBottomSheet(book: BookVO) {
        val bookJson = Gson().toJson(book)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottomsheet_book_option)
        dialog.show()
        dialog.llAddToShelves.setOnClickListener {
            dialog.dismiss()
            mPresenter.onTapAddToShelf(bookJson)
        }
        dialog.tvBottomSheetBookTitle.text = book.title
        dialog.tvBottomSheetBookAuthor.text = book.author
        Glide.with(this)
            .load("${book.bookImage}")
            .into(dialog.ivBottomSheetBook)
    }

    override fun navigateToBookDetail(book: BookVO) {
        val bookJson = Gson().toJson(book)
        startActivity(BookDetailActivity.newIntent(this, bookJson))
        overridePendingTransition(0, 0)
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }

    override fun navigateToAddToShelfScreen(bookJson: String) {
        startActivity(AddToShelvesActivity.newIntent(this, bookJson))
        overridePendingTransition(0, 0)
    }
}