package com.example.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.example.thelibraryapp.data.vos.OverviewListVO

interface BookModel {
    fun getOverview(
        onFailure: (String) -> Unit
    ): LiveData<List<OverviewListVO>>?
}