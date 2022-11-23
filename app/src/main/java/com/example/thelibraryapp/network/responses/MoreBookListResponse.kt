package com.example.thelibraryapp.network.responses

import com.example.thelibraryapp.data.vos.OverviewListVO
import com.google.gson.annotations.SerializedName

data class MoreBookListResponse(

    @SerializedName("status")
    val status: String?,

    @SerializedName("copyright")
    val copyright: String?,

    @SerializedName("results")
    val results: List<OverviewListVO>?
)