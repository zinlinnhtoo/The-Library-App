package com.example.thelibraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.AddToShelvesActivity
import com.example.thelibraryapp.activities.CreateShelfActivity
import com.example.thelibraryapp.adapters.ShelfAdapter
import kotlinx.android.synthetic.main.fragment_shelf.*

class ShelfFragment : Fragment() {

    private lateinit var mShelfAdapter : ShelfAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shelf, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpListener()
    }

    private fun setUpRecyclerView() {
        rvShelf.adapter = ShelfAdapter()
        rvShelf.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener() {
        btnCreateShelf.setOnClickListener {
            Toast.makeText(context, "Tap Create Shelf", Toast.LENGTH_SHORT).show()
            startActivity(context?.let { it1 -> CreateShelfActivity.newIntent(it1.applicationContext) })
            activity?.overridePendingTransition(0, 0)
        }
    }

}