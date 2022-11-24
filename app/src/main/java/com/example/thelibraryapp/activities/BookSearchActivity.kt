package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.BookSearchAdapter
import com.example.thelibraryapp.data.models.BookModel
import com.example.thelibraryapp.data.models.BookModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.google.gson.Gson
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_book_search.*
import java.util.concurrent.TimeUnit

class BookSearchActivity : AppCompatActivity(), BookViewHolderDelegate {

    private lateinit var mBookSearchAdapter: BookSearchAdapter

    private var mBookModel: BookModel = BookModelImpl

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BookSearchActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)

        mBookSearchAdapter = BookSearchAdapter(this)
        rvSearchBook.adapter = mBookSearchAdapter
        rvSearchBook.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

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
            .flatMap { mBookModel.searchGoogleBook(it.toString()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                Log.println(Log.INFO, "search book", it.toString())
                mBookSearchAdapter.setNewData(it)
            }, {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
            })
    }

    override fun onTapBook(book: BookVO) {
        val bookJson = Gson().toJson(book)
        startActivity(BookDetailActivity.newIntent(this, bookJson))

        mBookModel.insertBook(book) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}