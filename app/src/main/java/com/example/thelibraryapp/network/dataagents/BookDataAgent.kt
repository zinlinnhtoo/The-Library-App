package com.example.thelibraryapp.network.dataagents

import com.example.thelibraryapp.data.vos.OverviewListVO

interface BookDataAgent {
    fun getOverview(
        onSuccess: (List<OverviewListVO>) -> Unit,
        onFailure:  (String) -> Unit
    )
}