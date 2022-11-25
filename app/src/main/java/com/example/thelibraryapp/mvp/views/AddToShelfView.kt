package com.example.thelibraryapp.mvp.views

import com.example.thelibraryapp.data.vos.ShelfVO

interface AddToShelfView: BaseView {
    fun showShelfList(shelfList: List<ShelfVO>)
    fun closeAddToShelfScreen()
    fun onBackScreen()
}