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
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.GoToCategoryDelegate
import com.example.thelibraryapp.dummy.tabList
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.bottomNav

class HomeActivity : AppCompatActivity(), BookOptionDelegate, GoToCategoryDelegate {

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

        setUpBookCategoryRecyclerView()


    }

    private fun setUpBookCategoryRecyclerView() {
        mBookCategoryAdapter = BookCategoryAdapter(this, this)
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

    override fun onTapBookOption() {
        val bottomSheetBookOption = BottomSheetBehavior.from(bottomSheetBookOption)
        when {
            bottomSheetBookOption.state != BottomSheetBehavior.STATE_EXPANDED -> {
                bottomSheetBookOption.state = BottomSheetBehavior.STATE_EXPANDED
            }
            else -> {
                bottomSheetBookOption.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    override fun onTapCategory() {
        startActivity(BookCategoryActivity.newIntent(this@HomeActivity))
    }
}