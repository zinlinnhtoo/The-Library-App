package com.example.thelibraryapp.delegates

import com.example.thelibraryapp.data.vos.BookVO

interface BookOptionDelegate {
    fun onTapBookOption(
        book: BookVO
    )
}