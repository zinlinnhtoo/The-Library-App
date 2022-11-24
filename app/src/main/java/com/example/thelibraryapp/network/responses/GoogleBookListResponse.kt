package com.example.thelibraryapp.network.responses

import com.example.thelibraryapp.data.vos.ItemVO
import com.google.gson.annotations.SerializedName

data class GoogleBookListResponse(
    @SerializedName("items")
    val items: List<ItemVO>?
)
