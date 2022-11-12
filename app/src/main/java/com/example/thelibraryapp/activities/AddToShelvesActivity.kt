package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.AddToShelvesAdapter
import kotlinx.android.synthetic.main.activity_add_to_shelves.*

class AddToShelvesActivity : AppCompatActivity() {

    private lateinit var mAddToShelvesAdapter: AddToShelvesAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddToShelvesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_shelves)

        setUpRecyclerView()
        setUpListener()
    }

    private fun setUpListener() {
        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun setUpRecyclerView() {
        mAddToShelvesAdapter = AddToShelvesAdapter()
        rvAddToShelves.adapter = mAddToShelvesAdapter
        rvAddToShelves.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}