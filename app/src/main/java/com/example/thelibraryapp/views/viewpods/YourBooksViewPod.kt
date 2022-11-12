package com.example.thelibraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.LibraryLargeGridAdapter
import com.example.thelibraryapp.adapters.LibraryListBookAdapter
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.dummy.bookChip
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.bottomsheet_sort.*
import kotlinx.android.synthetic.main.bottomsheet_view_as.*
import kotlinx.android.synthetic.main.view_pod_your_books.view.*

class YourBooksViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs), BookOptionDelegate {

    lateinit var mLibraryListBookAdapter: LibraryListBookAdapter
    lateinit var mLibraryLargeGridAdapter: LibraryLargeGridAdapter

    override fun onFinishInflate() {

        setUpChip()

        setUpListRecyclerView()
        setUpLargeGridRecyclerView()

        ivSort.setOnClickListener {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.bottomsheet_sort)
            dialog.show()
            dialog.rbRecent.setOnClickListener {
                Toast.makeText(context, "Recent", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialog.rbTitle.setOnClickListener {
                Toast.makeText(context, "Title", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialog.rbAuthor.setOnClickListener {
                Toast.makeText(context, "Author", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

        }



        ivViewAs.setOnClickListener {
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.bottomsheet_view_as)
            dialog.show()
            dialog.rbList.setOnClickListener {
                Toast.makeText(context, "List", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                rvListBook.visibility = View.VISIBLE
                rvLargeGrid.visibility = View.GONE
            }
            dialog.rbLargeGrid.setOnClickListener {
                Toast.makeText(context, "Large Grid", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                rvLargeGrid.visibility = View.VISIBLE
                rvListBook.visibility = View.GONE
            }
            dialog.rbSmallGrid.setOnClickListener {
                Toast.makeText(context, "Small Grid", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                rvListBook.visibility = View.GONE
                rvLargeGrid.visibility = View.GONE
            }
        }



        super.onFinishInflate()

    }

    private fun setUpListRecyclerView() {
        mLibraryListBookAdapter = LibraryListBookAdapter(this)
        rvListBook.adapter = mLibraryListBookAdapter
        rvListBook.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
    
    private fun setUpLargeGridRecyclerView() {
        mLibraryLargeGridAdapter = LibraryLargeGridAdapter(this)
        rvLargeGrid.adapter = mLibraryLargeGridAdapter
        rvLargeGrid.layoutManager = GridLayoutManager(context, 2)
    }

    private fun setUpChip() {
        bookChip.forEach {
            val chip = createChip(it)
            chipGroupBook.addView(chip)
        }
    }

    private fun createChip(label: String): Chip {
        val chip = LayoutInflater.from(context).inflate(R.layout.filter_chip_book, chipGroupBook, false) as Chip
        chip.text = label
        chip.setOnClickListener {
            Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
        }
        return chip
    }

    override fun onTapBookOption() {
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(R.layout.bottomsheet_book_option)
        dialog.show()
    }
}