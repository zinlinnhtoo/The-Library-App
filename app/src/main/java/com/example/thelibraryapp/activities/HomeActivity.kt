package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.thelibraryapp.R
import com.example.thelibraryapp.adapters.BannerBookAdapter
import com.example.thelibraryapp.adapters.BookCategoryAdapter
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.OverviewListVO
import com.example.thelibraryapp.dummy.tabList
import com.example.thelibraryapp.mvp.presenters.HomePresenter
import com.example.thelibraryapp.mvp.presenters.HomePresenterImpl
import com.example.thelibraryapp.mvp.views.HomeView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottomsheet_book_option.*

class HomeActivity : AppCompatActivity(), HomeView {

    private lateinit var mBannerBookAdapter: BannerBookAdapter
    private lateinit var mBannerCarouselLayoutManager: CarouselLayoutManager
    private lateinit var mBookCategoryAdapter: BookCategoryAdapter

    private lateinit var mPresenter: HomePresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpPresenter()

        setUpBottomNavBar()

        setUpBannerBookCarouselRecyclerView()

        setUpBookTabLayout()

        setUpBookCategoryRecyclerView()

        rlSearchBar.setOnClickListener {
            mPresenter.onTapSearchBar()
        }

        mPresenter.onUiReady(this)

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[HomePresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun showBottomSheetDialog(book: BookVO) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottomsheet_book_option)
        dialog.show()
        dialog.llAddToShelves?.setOnClickListener {
            dialog.dismiss()
            mPresenter.onTapAddToShelf(book)
        }
        dialog.tvBottomSheetBookTitle.text = book.title
        dialog.tvBottomSheetBookAuthor.text = book.author
        Glide.with(this)
            .load("${book.bookImage}")
            .into(dialog.ivBottomSheetBook)
    }

    private fun setUpBookCategoryRecyclerView() {
        mBookCategoryAdapter = BookCategoryAdapter(mPresenter, mPresenter, mPresenter)
        rvBookCategory.adapter = mBookCategoryAdapter
        rvBookCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpBookTabLayout() {
        tabList.forEach {
            tabLayoutBook.newTab().apply {
                text = it
                tabLayoutBook.addTab(this)
            }
        }
    }

    private fun setUpBannerBookCarouselRecyclerView() {
        mBannerBookAdapter = BannerBookAdapter(mPresenter, mPresenter)
        rvBannerBook.adapter = mBannerBookAdapter
        rvBannerBook.setIntervalRatio(0.8f)
        mBannerCarouselLayoutManager = rvBannerBook.getCarouselLayoutManager()
    }

    private fun setUpBottomNavBar() {
        bottomNav.apply {
            selectedItemId = R.id.action_home
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_home -> {
                    }
                    R.id.action_library -> {
                        startActivity(LibraryActivity.newIntent(this@HomeActivity))
                        overridePendingTransition(0, 0)
                    }
                }
                true
            }
        }
    }

    override fun showBookList(overViewList: List<OverviewListVO>) {
        mBookCategoryAdapter.setNewData(overViewList)
    }

    override fun showBannerCarousel(bookList: List<BookVO>) {
        mBannerBookAdapter.setNewData(bookList)
    }

    override fun showBookOptionBottomSheet(book: BookVO) {
        showBottomSheetDialog(book)
    }

    override fun navigateToBookDetailScreen(bookJson: String) {
        startActivity(BookDetailActivity.newIntent(this, bookJson))
    }

    override fun navigateToBookCategoryScreen(categoryTitle: String) {
        startActivity(BookCategoryActivity.newIntent(this@HomeActivity, categoryTitle))
    }

    override fun navigateToAddToShelfScreen(bookJson: String) {
        startActivity(AddToShelvesActivity.newIntent(this, bookJson))
        overridePendingTransition(0, 0)
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }

    override fun navigateToBookSearchScreen() {
        startActivity(BookSearchActivity.newIntent(this))
    }
}