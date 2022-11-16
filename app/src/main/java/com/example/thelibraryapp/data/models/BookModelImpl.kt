package com.example.thelibraryapp.data.models

import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.ListVO
import com.example.thelibraryapp.network.dataagents.BookDataAgent
import com.example.thelibraryapp.network.dataagents.BookDataAgentImpl

object BookModelImpl: BookModel {

    private val mBookDataAgent: BookDataAgent = BookDataAgentImpl

    override fun getOverview(onSuccess: (List<ListVO>) -> Unit, onFailure: (String) -> Unit) {
        mBookDataAgent.getOverview(
            onSuccess,
            onFailure
        )
    }

}