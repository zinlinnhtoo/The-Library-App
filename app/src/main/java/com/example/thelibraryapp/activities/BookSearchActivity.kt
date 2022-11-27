package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.BookSearchAdapter
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.mvp.presenters.BookSearchPresenter
import com.example.thelibraryapp.mvp.presenters.BookSearchPresenterImpl
import com.example.thelibraryapp.mvp.views.BookSearchView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_book_search.*
import java.util.concurrent.TimeUnit

class BookSearchActivity : AppCompatActivity(), BookSearchView {

    private lateinit var mBookSearchAdapter: BookSearchAdapter
    private lateinit var mPresenter: BookSearchPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BookSearchActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)
        setUpPresenter()
        setUpRecyclerView()

//        etSearch.textChanges().debounce(500L, TimeUnit.MILLISECONDS).subscribe {
//            mBookModel.searchGoogleBook(it.toString())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    Log.println(Log.INFO, "search book", it.toString())
//                    mBookSearchAdapter.setNewData(it)
//                },
//                    {
//                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
//                    })
//        }

        etSearch.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap { mPresenter.onSearchTextChanges(it.toString()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                mPresenter.onSearchBookList(it)

            }, {
                mPresenter.onThrowableError(it.localizedMessage.orEmpty())
            })

        mPresenter.onUiReady(this)
    }

    private fun setUpRecyclerView() {
        mBookSearchAdapter = BookSearchAdapter(mPresenter)
        rvSearchBook.adapter = mBookSearchAdapter
        rvSearchBook.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[BookSearchPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    override fun showSearchedBookList(bookList: List<BookVO>) {
        mBookSearchAdapter.setNewData(bookList)
    }

    override fun navigateToBookDetail(book: BookVO) {
        val bookJson = Gson().toJson(book)
        startActivity(BookDetailActivity.newIntent(this, bookJson))
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }
}