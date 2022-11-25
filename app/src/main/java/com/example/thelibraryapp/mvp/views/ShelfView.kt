package com.example.thelibraryapp.mvp.views

import com.example.thelibraryapp.data.vos.ShelfVO

interface ShelfView :
    BaseView {
        fun showShelfList(shelfList: List<ShelfVO>)
        fun navigateToShelfDetail(shelf: ShelfVO)
        fun navigateToCreateShelfScreen()
}