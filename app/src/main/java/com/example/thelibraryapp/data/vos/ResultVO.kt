package com.example.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class ResultVO(

    @SerializedName("lists")
    val lists: List<ListVO>

)
