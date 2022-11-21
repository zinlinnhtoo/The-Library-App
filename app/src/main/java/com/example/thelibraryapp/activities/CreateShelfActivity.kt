package com.example.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.example.thelibraryapp.R
import com.example.thelibraryapp.data.models.ShelfModel
import com.example.thelibraryapp.data.models.ShelfModelImpl
import com.example.thelibraryapp.data.vos.ShelfVO
import kotlinx.android.synthetic.main.activity_create_shelf.*

class CreateShelfActivity : AppCompatActivity() {

    private val mShelfModel: ShelfModel = ShelfModelImpl

    private var mShelfTitle: String = ""
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CreateShelfActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shelf)

        setUpListener()

    }

    private fun setUpListener() {
        ivBtnBack.setOnClickListener {
            super.onBackPressed()
        }

        etShelfName.setOnEditorActionListener { _, i, _ ->
            if(i == EditorInfo.IME_ACTION_DONE) {
                mShelfTitle = etShelfName.text.toString()
                mShelfModel.insertShelf(ShelfVO(mShelfTitle, listOf()))
                finish()
                return@setOnEditorActionListener true
            }
            false
        }

    }
}