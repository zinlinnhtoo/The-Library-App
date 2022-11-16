package com.example.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class BookVO(

    @SerializedName("title")
    val title: String?,

    @SerializedName("author")
    val author: String?,

    @SerializedName("book_image")
    val bookImage: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("book_image_width")
    val bookWidth: Int?,

    @SerializedName("book_image_height")
    val bookHeight: Int?
)
