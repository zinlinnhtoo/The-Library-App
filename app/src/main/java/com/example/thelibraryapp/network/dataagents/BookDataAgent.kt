package com.example.thelibraryapp.network.dataagents

import com.example.thelibraryapp.data.vos.ListVO
import com.example.thelibraryapp.network.responses.BookListResponse

interface BookDataAgent {
    fun getOverview(
        onSuccess: (List<ListVO>) -> Unit,
        onFailure:  (String) -> Unit
    )
}