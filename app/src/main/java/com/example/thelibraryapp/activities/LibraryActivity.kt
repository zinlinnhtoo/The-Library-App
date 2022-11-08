package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thelibraryapp.R
import kotlinx.android.synthetic.main.activity_home.*

class LibraryActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LibraryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        setUpBottomNavBar()
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