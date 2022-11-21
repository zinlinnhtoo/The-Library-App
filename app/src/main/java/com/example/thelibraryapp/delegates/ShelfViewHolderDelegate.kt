package com.example.thelibraryapp.delegates

import com.example.thelibraryapp.data.vos.ShelfVO

interface ShelfViewHolderDelegate {
    fun onTapShelf(
        shelf: ShelfVO
    )
}