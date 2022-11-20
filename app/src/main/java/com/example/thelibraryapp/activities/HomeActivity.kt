package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.BannerBookAdapter
import com.example.thelibraryapp.adapters.BookCategoryAdapter
import com.example.thelibraryapp.data.models.BookModel
import com.example.thelibraryapp.data.models.BookModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.GoToCategoryDelegate
import com.example.thelibraryapp.dummy.tabList
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.bottomNav
import kotlinx.android.synthetic.main.bottomsheet_book_option.*

class HomeActivity : AppCompatActivity(), BookOptionDelegate, GoToCategoryDelegate, BookViewHolderDelegate {

    private val mBookModel: BookModel = BookModelImpl

    private lateinit var mBannerBookAdapter: BannerBookAdapter
    private lateinit var mBannerCarouselLayoutManager: CarouselLayoutManager

    private lateinit var mBookCategoryAdapter: BookCategoryAdapter


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpBottomNavBar()

        setUpBannerBookCarouselRecyclerView()

        setUpBookTabLayout()

        setUpBookCategoryRecyclerView()

        requestData()

    }

    private fun requestData() {
        mBookModel.getOverview {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }?.observe(this) {
            mBookCategoryAdapter.setNewData(it)
        }

        mBookModel.getReadBook {
            Toast.makeText(this, "get read book error", Toast.LENGTH_SHORT).show()
        }?.observe(this) {
            mBannerBookAdapter.setNewData(it)
//            mBannerCarouselLayoutManager.scrollToPosition(it.lastIndex)
        }
    }

    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottomsheet_book_option)
        dialog.show()
        dialog.llAddToShelves?.setOnClickListener {
            dialog.dismiss()
            startActivity(AddToShelvesActivity.newIntent(this))
            overridePendingTransition(0, 0)
        }
    }

    private fun setUpBookCategoryRecyclerView() {
        mBookCategoryAdapter = BookCategoryAdapter(this, this, this)
        rvBookCategory.adapter = mBookCategoryAdapter
        rvBookCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpBookTabLayout() {
        tabList.forEach {
            tabLayoutBook.newTab().apply {
                text = it
                tabLayoutBook.addTab(this)
            }
        }
    }

    private fun setUpBannerBookCarouselRecyclerView() {
        mBannerBookAdapter = BannerBookAdapter(this)
        rvBannerBook.adapter = mBannerBookAdapter
        rvBannerBook.setIntervalRatio(0.8f)
        mBannerCarouselLayoutManager = rvBannerBook.getCarouselLayoutManager()
    }

    private fun setUpBottomNavBar() {
        bottomNav.apply {
            selectedItemId = R.id.action_home
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_home -> {
                    }
                    R.id.action_library -> {
                        startActivity(LibraryActivity.newIntent(this@HomeActivity))
                        overridePendingTransition(0, 0)
                    }
                }
                true
            }
        }
    }

    override fun onTapBookOption() {
        showBottomSheetDialog()
    }


    override fun onTapCategory() {
        startActivity(BookCategoryActivity.newIntent(this@HomeActivity))
    }

    override fun onTapBook(book: BookVO) {
        mBookModel.insertBook(book, onFailure = {
            Toast.makeText(this, "insert book error", Toast.LENGTH_SHORT).show()
        })
        val bookJson = Gson().toJson(book)
        startActivity(BookDetailActivity.newIntent(this, bookJson))
    }
}