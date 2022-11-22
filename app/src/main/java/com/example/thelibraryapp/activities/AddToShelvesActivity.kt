package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.AddToShelvesAdapter
import com.example.thelibraryapp.data.models.ShelfModel
import com.example.thelibraryapp.data.models.ShelfModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.delegates.AddToShelfCheckboxDelegate
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_to_shelves.*

class AddToShelvesActivity : AppCompatActivity(), AddToShelfCheckboxDelegate {


    private lateinit var mAddToShelvesAdapter: AddToShelvesAdapter

    private val mShelfModel: ShelfModel = ShelfModelImpl

    private var mShelfList = mutableListOf<ShelfVO>()

    private var mBook: BookVO? = null
    private var mBookJson: String? = null


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

        getExtra()
        setUpRecyclerView()
        setUpListener()
        requestData()
    }

    private fun getExtra() {
        mBookJson = intent?.getStringExtra(EXTRA_BOOK_ADD_TO_SHELVES)

        mBookJson?.let {
            mBook = Gson().fromJson(it, BookVO::class.java)
        }
    }

    private fun requestData() {
        mShelfModel.getAllShelves()
            ?.observe(this) {
                mAddToShelvesAdapter.setNewData(it)
            }
    }

    private fun setUpListener() {
        btnClose.setOnClickListener {
            finish()
        }

        btnAddToShelves.setOnClickListener {
            mShelfList.forEach { shelf ->
                mBook?.let { book ->
                    if (shelf.books?.contains(book) == false) {
                        shelf.books.add(book)
                    }
                    mShelfModel.insertShelf(shelf)
                }
            }
            super.onBackPressed()
        }
    }

    private fun setUpRecyclerView() {
        mAddToShelvesAdapter = AddToShelvesAdapter(this)
        rvAddToShelves.adapter = mAddToShelvesAdapter
        rvAddToShelves.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCheckedCheckbox(shelf: ShelfVO, isChecked: Boolean) {
        if (isChecked) {
            mShelfList.add(shelf)
        } else {
            mShelfList.remove(shelf)
        }
    }
}