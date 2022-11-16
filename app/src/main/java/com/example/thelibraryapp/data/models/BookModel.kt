package com.example.thelibraryapp.data.models

import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ListVO

interface BookModel {
    fun getOverview(
        onSuccess: (List<ListVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}