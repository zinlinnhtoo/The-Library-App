package com.example.thelibraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.AddToShelvesActivity
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.views.viewpods.YourBooksViewPod
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_book_option.*
import kotlinx.android.synthetic.main.fragment_book.*


class BookFragment : Fragment(), BookOptionDelegate {

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
    }

    private fun setUpViewPods() {
        mYourBooksViewPod = vpYourBooks as YourBooksViewPod
        mYourBooksViewPod.setUpYourBooksViewPod(this)
    }

    override fun onTapBookOption() {
        val dialog = context?.let { BottomSheetDialog(it) }
        dialog?.setContentView(R.layout.bottomsheet_book_option)
        dialog?.show()
        dialog?.llAddToShelves?.setOnClickListener {
            Toast.makeText(context, "Tap Add To Shelves", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            startActivity(context?.let { it1 -> AddToShelvesActivity.newIntent(it1.applicationContext) })
            activity?.overridePendingTransition(0, 0)
        }
    }

}