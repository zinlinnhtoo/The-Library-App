package com.example.thelibraryapp.network.responses

import com.example.thelibraryapp.data.vos.ResultVO
import com.google.gson.annotations.SerializedName

data class BookListResponse(

    @SerializedName("status")
    val status: String?,

    @SerializedName("copyright")
    val copyright: String?,

    @SerializedName("num_results")
    val numResult: Int?,

    @SerializedName("results")
    val results: ResultVO

)
