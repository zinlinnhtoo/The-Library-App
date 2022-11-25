package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.vos.ShelfVO
import com.example.thelibraryapp.mvp.presenters.CreateShelfPresenter
import com.example.thelibraryapp.mvp.presenters.CreateShelfPresenterImpl
import com.example.thelibraryapp.mvp.views.CreateShelfView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_shelf.*

class CreateShelfActivity : AppCompatActivity(), CreateShelfView {

    private lateinit var mPresenter: CreateShelfPresenter

    private var mShelfTitle: String = ""
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CreateShelfActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shelf)
        setUpPresenter()

        setUpListener()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[CreateShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListener() {
        ivBtnBack.setOnClickListener {
            mPresenter.onTapBackButton()
        }

        etShelfName.setOnEditorActionListener { _, i, _ ->
            if(i == EditorInfo.IME_ACTION_DONE) {
                mShelfTitle = etShelfName.text.toString()
                mPresenter.insertSheff(shelf = ShelfVO(mShelfTitle, mutableListOf()))
                return@setOnEditorActionListener true
            }
            false
        }

    }

    override fun onBackPress() {
        super.onBackPressed()
    }

    override fun finishScreen() {
        finish()
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }
}