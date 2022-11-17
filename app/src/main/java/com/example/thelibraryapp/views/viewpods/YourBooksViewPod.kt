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
import com.example.thelibraryapp.adapters.LibraryLargeGridBookAdapter
import com.example.thelibraryapp.adapters.LibraryListBookAdapter
import com.example.thelibraryapp.adapters.LibrarySmallGridBookAdapter
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.dummy.bookChip
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.bottomsheet_sort.*
import kotlinx.android.synthetic.main.bottomsheet_view_as.*
import kotlinx.android.synthetic.main.view_pod_your_books.view.*

class YourBooksViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs){

    lateinit var mLibraryListBookAdapter: LibraryListBookAdapter
    lateinit var mLibraryLargeGridBookAdapter: LibraryLargeGridBookAdapter
    lateinit var mLibrarySmallGridBookAdapter: LibrarySmallGridBookAdapter

    lateinit var mBookOptionDelegate: BookOptionDelegate
    lateinit var mBookViewHolderDelegate: BookViewHolderDelegate

    override fun onFinishInflate() {
        setUpChip()
        ivSort.setOnClickListener {
            setUpBottomsheetForSort()

        }
        ivViewAs.setOnClickListener {
            setUpBottomsheetForList()
        }

        super.onFinishInflate()

    }

    fun setData(bookList: List<BookVO>) {
        mLibraryListBookAdapter.setNewData(bookList)
        mLibraryLargeGridBookAdapter.setNewData(bookList)
        mLibrarySmallGridBookAdapter.setNewData(bookList)
    }

    private fun setUpBottomsheetForList() {
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(R.layout.bottomsheet_view_as)
        dialog.show()
        dialog.rbList.setOnClickListener {
            Toast.makeText(context, "List", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            rvListBook.visibility = VISIBLE
            rvLargeGrid.visibility = GONE
            rvSmallGrid.visibility = GONE
        }
        dialog.rbLargeGrid.setOnClickListener {
            Toast.makeText(context, "Large Grid", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            rvLargeGrid.visibility = VISIBLE
            rvListBook.visibility = GONE
            rvSmallGrid.visibility = GONE
        }
        dialog.rbSmallGrid.setOnClickListener {
            Toast.makeText(context, "Small Grid", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            rvSmallGrid.visibility = VISIBLE
            rvListBook.visibility = GONE
            rvLargeGrid.visibility = GONE
        }
    }

    private fun setUpBottomsheetForSort() {
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

    fun setUpYourBooksViewPod(
        bookOptionDelegate: BookOptionDelegate,
        bookViewHolderDelegate: BookViewHolderDelegate
    ) {
        setDelegate(bookOptionDelegate, bookViewHolderDelegate)
        setUpListRecyclerView()
        setUpLargeGridRecyclerView()
        setUpSmallGridRecyclerView()
    }

    private fun setDelegate(
        bookOptionDelegate: BookOptionDelegate,
        bookViewHolderDelegate: BookViewHolderDelegate
    ) {
        this.mBookOptionDelegate = bookOptionDelegate
        this.mBookViewHolderDelegate = bookViewHolderDelegate
    }

    private fun setUpListRecyclerView() {
        mLibraryListBookAdapter = LibraryListBookAdapter(mBookOptionDelegate, mBookViewHolderDelegate)
        rvListBook.adapter = mLibraryListBookAdapter
        rvListBook.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
    
    private fun setUpLargeGridRecyclerView() {
        mLibraryLargeGridBookAdapter = LibraryLargeGridBookAdapter(mBookOptionDelegate, mBookViewHolderDelegate)
        rvLargeGrid.adapter = mLibraryLargeGridBookAdapter
        rvLargeGrid.layoutManager = GridLayoutManager(context, 2)
    }

    private fun setUpSmallGridRecyclerView() {
        mLibrarySmallGridBookAdapter = LibrarySmallGridBookAdapter(mBookOptionDelegate, mBookViewHolderDelegate)
        rvSmallGrid.adapter = mLibrarySmallGridBookAdapter
        rvSmallGrid.layoutManager = GridLayoutManager(context, 3)
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
}