package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.LibraryViewPagerAdapter
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.bottomNav
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.bottomsheet_book_option.*

class LibraryActivity : AppCompatActivity(){

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LibraryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        setUpBottomNavBar()

        setUpViewpagerAndTabLayout()
    }

    private fun setUpViewpagerAndTabLayout() {
        val libraryViewPagerAdapter = LibraryViewPagerAdapter(this)
        viewPagerLibrary.adapter = libraryViewPagerAdapter

        TabLayoutMediator(tabLayoutLibrary, viewPagerLibrary) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Your books"
                }
                1 -> {
                    tab.text = "Your shelves"
                }
            }
        }.attach()
    }

    private fun setUpBottomNavBar() {
        bottomNav.apply {
            selectedItemId = R.id.action_library
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_home -> {
                        startActivity(HomeActivity.newIntent(this@LibraryActivity))
                        overridePendingTransition(0, 0)
                    }
                    R.id.action_library -> {
                    }
                }
                true
            }
        }
    }
}