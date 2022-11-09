package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.BannerBookAdapter
import com.example.thelibraryapp.adapters.BookCategoryAdapter
import com.example.thelibraryapp.dummy.tabList
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.bottomNav
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.view_holder_book_category.*

class HomeActivity : AppCompatActivity() {

    private lateinit var mBannerBookAdapter: BannerBookAdapter

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

        mBookCategoryAdapter = BookCategoryAdapter()
        rvBookCategory.adapter = mBookCategoryAdapter
        rvBookCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
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
        mBannerBookAdapter = BannerBookAdapter()
        rvBannerBook.adapter = mBannerBookAdapter
        rvBannerBook.setIntervalRatio(0.8f)
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
}