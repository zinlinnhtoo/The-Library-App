package com.example.thelibraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.CreateShelfActivity
import com.example.thelibraryapp.activities.ShelfDetailActivity
import com.example.thelibraryapp.adapters.ShelfAdapter
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.mvp.presenters.ShelfPresenter
import com.example.thelibraryapp.mvp.presenters.ShelfPresenterImpl
import com.example.thelibraryapp.mvp.views.ShelfView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_shelf.*

class ShelfFragment : Fragment(), ShelfView {

    private lateinit var mPresenter: ShelfPresenter

    private lateinit var mShelfAdapter : ShelfAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shelf, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()

        setUpRecyclerView()
        setUpListener()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[ShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpRecyclerView() {
        mShelfAdapter = ShelfAdapter(mPresenter)
        rvShelf.adapter = mShelfAdapter
        rvShelf.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener() {
        btnCreateShelf.setOnClickListener {
            mPresenter.onTapCreateShelf()
        }
    }

    override fun showShelfList(shelfList: List<ShelfVO>) {
        mShelfAdapter.setNewData(shelfList)
    }

    override fun navigateToShelfDetail(shelf: ShelfVO) {
        val shelfJson = Gson().toJson(shelf)
        startActivity(context?.let { it -> ShelfDetailActivity.newIntent(it.applicationContext, shelfJson) })
        activity?.overridePendingTransition(0, 0)
    }

    override fun navigateToCreateShelfScreen() {
        startActivity(context?.let { it1 -> CreateShelfActivity.newIntent(it1.applicationContext) })
        activity?.overridePendingTransition(0, 0)
    }

    override fun showError(errorString: String) {
        Snackbar.make(requireActivity().window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }
}