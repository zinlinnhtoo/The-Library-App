package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.AddToShelvesAdapter
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.mvp.presenters.AddToShelfPresenter
import com.example.thelibraryapp.mvp.presenters.AddToShelfPresenterImpl
import com.example.thelibraryapp.mvp.views.AddToShelfView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_to_shelves.*

class AddToShelvesActivity : AppCompatActivity(), AddToShelfView {


    private lateinit var mAddToShelvesAdapter: AddToShelvesAdapter
    private var mBookJson: String? = null

    private lateinit var mPresenter: AddToShelfPresenter

    companion object {
        const val EXTRA_BOOK_ADD_TO_SHELVES = "EXTRA_BOOK_ADD_TO_SHELVES"

        fun newIntent(context: Context, bookJson: String): Intent {
            return Intent(context, AddToShelvesActivity::class.java)
                .putExtra(EXTRA_BOOK_ADD_TO_SHELVES, bookJson)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_shelves)

        setUpPresenter()

        getExtra()
        setUpRecyclerView()
        setUpListener()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[AddToShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun getExtra() {
        mBookJson = intent?.getStringExtra(EXTRA_BOOK_ADD_TO_SHELVES)
        mBookJson?.let {
            mPresenter.getBookExtra(Gson().fromJson(it, BookVO::class.java))
        }
    }

    private fun setUpListener() {
        btnClose.setOnClickListener {
            mPresenter.onTapCloseButton()
        }

        btnAddToShelves.setOnClickListener {
            mPresenter.onTapAddToShelfButton()
        }
    }

    private fun setUpRecyclerView() {
        mAddToShelvesAdapter = AddToShelvesAdapter(mPresenter)
        rvAddToShelves.adapter = mAddToShelvesAdapter
        rvAddToShelves.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun showShelfList(shelfList: List<ShelfVO>) {
        mAddToShelvesAdapter.setNewData(shelfList)
    }

    override fun closeAddToShelfScreen() {
        finish()
    }

    override fun onBackScreen() {
        super.onBackPressed()
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }
}