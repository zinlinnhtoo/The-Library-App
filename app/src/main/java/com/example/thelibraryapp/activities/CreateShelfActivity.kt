package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.models.ShelfModel
import com.example.thelibraryapp.data.models.ShelfModelImpl
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ShelfVO
import kotlinx.android.synthetic.main.activity_create_shelf.*

class CreateShelfActivity : AppCompatActivity() {

    private val mShelfModel: ShelfModel = ShelfModelImpl

    private var mShelfTitle: String = ""
    private var bookList = listOf<BookVO>()
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CreateShelfActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shelf)

        setUpListener()
        val book = BookVO(
            id = 2,
            title = null,
            author = null,
            bookImage = null,
            description = null,
            bookCategory = null,
            bookHeight = null,
            bookWidth = null,
            updatedDate = null
        )
        bookList = listOf<BookVO>(book)

    }

    private fun setUpListener() {
        ivBtnBack.setOnClickListener {
            super.onBackPressed()
        }

        etShelfName.setOnEditorActionListener { _, i, _ ->
            if(i == EditorInfo.IME_ACTION_DONE) {
//                Toast.makeText(this, etShelfName.text.toString(), Toast.LENGTH_SHORT).show()
                mShelfTitle = etShelfName.text.toString()
                Log.println(Log.INFO, "Shelf", mShelfTitle)
                mShelfModel.insertShelf(ShelfVO(mShelfTitle, listOf()),
                    onFailure = {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    })
                return@setOnEditorActionListener true
            }
            false
        }

    }
}