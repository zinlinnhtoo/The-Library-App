package com.example.thelibraryapp.data.models

import com.example.thelibraryapp.data.vos.OverviewListVO

interface BookModel {
    fun getOverview(
        onSuccess: (List<OverviewListVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}