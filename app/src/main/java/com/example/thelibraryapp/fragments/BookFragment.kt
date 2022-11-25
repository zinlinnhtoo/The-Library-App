package com.example.thelibraryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.AddToShelvesActivity
import com.example.thelibraryapp.activities.BookDetailActivity
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.mvp.presenters.BookPresenter
import com.example.thelibraryapp.mvp.presenters.BookPresenterImpl
import com.example.thelibraryapp.mvp.views.BookView
import com.example.thelibraryapp.views.viewpods.YourBooksViewPod
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.bottomsheet_book_option.*
import kotlinx.android.synthetic.main.fragment_book.*


class BookFragment : Fragment(), BookView {

    private lateinit var mPresenter: BookPresenter

    lateinit var mYourBooksViewPod: YourBooksViewPod

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpViewPods()
        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[BookPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpViewPods() {
        mYourBooksViewPod = vpYourBooks as YourBooksViewPod
        mYourBooksViewPod.setUpYourBooksViewPod(mPresenter, mPresenter)
    }

    override fun showBookList(booList: List<BookVO>) {
        mYourBooksViewPod.setData(booList)
    }

    override fun navigateToAddToShelf(book: BookVO) {
        val bookJson = Gson().toJson(book)
        val dialog = context?.let { BottomSheetDialog(it) }
        dialog?.setContentView(R.layout.bottomsheet_book_option)
        dialog?.show()
        dialog?.llAddToShelves?.setOnClickListener {
            Toast.makeText(context, "Tap Add To Shelves", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            startActivity(context?.let { it1 -> AddToShelvesActivity.newIntent(it1.applicationContext, bookJson) })
            activity?.overridePendingTransition(0, 0)
        }
        dialog?.tvBottomSheetBookTitle?.text = book.title
        dialog?.tvBottomSheetBookAuthor?.text = book.author
        dialog?.ivBottomSheetBook?.let {
            Glide.with(this)
                .load("${book.bookImage}")
                .into(it)
        }
    }

    override fun navigateToBookDetail(book: BookVO) {
        val bookJson = Gson().toJson(book)
        startActivity(context?.let {
            BookDetailActivity.newIntent(it.applicationContext, bookJson)
        })
        activity?.overridePendingTransition(0, 0)
    }

    override fun showError(errorString: String) {
        Snackbar.make(requireActivity().window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }
}