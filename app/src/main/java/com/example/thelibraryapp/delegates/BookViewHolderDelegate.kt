package com.example.thelibraryapp.delegates

import com.example.thelibraryapp.data.vos.BookVO

interface BookViewHolderDelegate {
    fun onTapBook(
        book: BookVO
    )
}