package com.example.thelibraryapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.FilterChipAdapter
import com.example.thelibraryapp.adapters.LibraryLargeGridBookAdapter
import com.example.thelibraryapp.adapters.LibraryListBookAdapter
import com.example.thelibraryapp.adapters.LibrarySmallGridBookAdapter
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.CategoryVO
import com.example.thelibraryapp.delegates.BookOptionDelegate
import com.example.thelibraryapp.delegates.BookViewHolderDelegate
import com.example.thelibraryapp.delegates.FilterChipDelegate
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_sort.*
import kotlinx.android.synthetic.main.bottomsheet_view_as.*
import kotlinx.android.synthetic.main.view_pod_your_books.view.*

class YourBooksViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs), FilterChipDelegate{

    lateinit var mFilterChipAdapter: FilterChipAdapter
    lateinit var mLibraryListBookAdapter: LibraryListBookAdapter
    lateinit var mLibraryLargeGridBookAdapter: LibraryLargeGridBookAdapter
    lateinit var mLibrarySmallGridBookAdapter: LibrarySmallGridBookAdapter

    lateinit var mBookOptionDelegate: BookOptionDelegate
    lateinit var mBookViewHolderDelegate: BookViewHolderDelegate

    var mBookList: List<BookVO> = listOf()
    var mSortedBookList: List<BookVO> = listOf()
    var mCategoryList: MutableList<String> = mutableListOf()
    var mCategoryVOList: MutableList<CategoryVO> = mutableListOf()
    var mCategorySortedList: MutableList<CategoryVO> = mutableListOf()

    override fun onFinishInflate() {
        ivSort.setOnClickListener {
            setUpBottomSheetForSort()

        }
        ivViewAs.setOnClickListener {
            setUpBottomSheetForList()
        }
        btnClearFilter.setOnClickListener {
            setData(mBookList)
            btnClearFilter.visibility = View.GONE
        }

        super.onFinishInflate()

    }

    fun setData(bookList: List<BookVO>) {
        mBookList = bookList
        mSortedBookList = bookList
        bookList.forEach {
            mCategoryList.add(it.bookCategory.orEmpty())
        }
        val distinctCategoryList = mCategoryList.distinct()
        distinctCategoryList.forEach {
            mCategoryVOList.add(CategoryVO(category = it))
            mCategorySortedList.add(CategoryVO(category = it))
        }
        mFilterChipAdapter.setNewData(mCategoryVOList.distinct())
        setUpDataForRecyclerView(bookList)
    }

    private fun setUpDataForRecyclerView(bookList: List<BookVO>) {
        mLibraryListBookAdapter.setNewData(bookList)
        mLibraryLargeGridBookAdapter.setNewData(bookList)
        mLibrarySmallGridBookAdapter.setNewData(bookList)
    }

    private fun setUpBottomSheetForList() {
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

    private fun setUpBottomSheetForSort() {
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(R.layout.bottomsheet_sort)
        dialog.show()
        dialog.rbRecent.setOnClickListener {
            Toast.makeText(context, "Recent", Toast.LENGTH_SHORT).show()
            val sortedListByDate = mSortedBookList
            setData(sortedListByDate)
            tvSort.text = "Sort by: Recent"
            dialog.dismiss()
        }
        dialog.rbTitle.setOnClickListener {
            Toast.makeText(context, "Title", Toast.LENGTH_SHORT).show()
            val sortedListByTitle = mSortedBookList.sortedBy { it.title }
            setData(sortedListByTitle)
            tvSort.text = "Sort by: Title"
            dialog.dismiss()
        }
        dialog.rbAuthor.setOnClickListener {
            Toast.makeText(context, "Author", Toast.LENGTH_SHORT).show()
            val sortedListByAuthor = mSortedBookList.sortedBy { it.author }
            setData(sortedListByAuthor)
            tvSort.text = "Sort by: Author"
            dialog.dismiss()
        }
    }

    fun setUpYourBooksViewPod(
        bookOptionDelegate: BookOptionDelegate,
        bookViewHolderDelegate: BookViewHolderDelegate,
    ) {
        setDelegate(bookOptionDelegate, bookViewHolderDelegate)
        setUpFilterChipRecyclerView()
        setUpListRecyclerView()
        setUpLargeGridRecyclerView()
        setUpSmallGridRecyclerView()
    }

    private fun setDelegate(
        bookOptionDelegate: BookOptionDelegate,
        bookViewHolderDelegate: BookViewHolderDelegate,
    ) {
        this.mBookOptionDelegate = bookOptionDelegate
        this.mBookViewHolderDelegate = bookViewHolderDelegate
    }

    private fun setUpFilterChipRecyclerView() {
        mFilterChipAdapter = FilterChipAdapter(this)
        rvChip.adapter = mFilterChipAdapter
        rvChip.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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

    override fun onTapChip(category: CategoryVO) {
        if (category.isChecked) {
            btnClearFilter.visibility = View.GONE
            mFilterChipAdapter.setNewData(mCategoryVOList)
            setUpDataForRecyclerView(mBookList)
            mSortedBookList = mBookList
        } else {
            btnClearFilter.visibility = View.VISIBLE
            mSortedBookList = mBookList.filter { it.bookCategory == category.category }
            Toast.makeText(context, category.isChecked.toString(), Toast.LENGTH_SHORT).show()
            setUpDataForRecyclerView(mSortedBookList    )
            mCategorySortedList.forEach{
                if (it.category == category.category) {
                    it.isChecked = it.isChecked != true
                } else it.isChecked = false
            }
            val sortedCategory = mCategorySortedList.sortedByDescending { it.isChecked }
            mFilterChipAdapter.setNewData(sortedCategory.distinctBy { it.category })
        }
    }
}