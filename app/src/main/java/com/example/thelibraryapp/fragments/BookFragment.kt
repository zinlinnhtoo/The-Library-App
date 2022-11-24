package com.example.thelibraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.AddToShelvesActivity
import com.example.thelibraryapp.activities.BookDetailActivity
import com.example.thelibraryapp.data.models.BookModel
import com.example.thelibraryapp.data.models.BookModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.CategoryVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.FilterChipDelegate
import com.example.thelibraryapp.views.viewpods.YourBooksViewPod
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.bottomsheet_book_option.*
import kotlinx.android.synthetic.main.fragment_book.*


class BookFragment : Fragment(), BookOptionDelegate, BookViewHolderDelegate {

    private val mBookModel: BookModel = BookModelImpl

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
        setUpViewPods()
        requestData()
    }

    private fun requestData() {
        mBookModel.getReadBook()?.observe(viewLifecycleOwner) {
            mYourBooksViewPod.setData(it)
        }
    }

    private fun setUpViewPods() {
        mYourBooksViewPod = vpYourBooks as YourBooksViewPod
        mYourBooksViewPod.setUpYourBooksViewPod(this, this)
    }

    override fun onTapBookOption(book: BookVO) {
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

    override fun onTapBook(book: BookVO) {
        val bookJson = Gson().toJson(book)
        startActivity(context?.let {
            BookDetailActivity.newIntent(it.applicationContext, bookJson)
        })
        activity?.overridePendingTransition(0, 0)
    }
}