package com.example.thelibraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
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
    }

    private fun setUpRecyclerView() {
        rvShelf.adapter = ShelfAdapter()
        rvShelf.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}