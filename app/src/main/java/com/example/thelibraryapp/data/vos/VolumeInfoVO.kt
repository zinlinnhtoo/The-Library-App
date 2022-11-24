package com.example.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class VolumeInfoVO(
    @SerializedName("title")
    val title: String?,

    @SerializedName("authors")
    val authors: List<String>?,

    @SerializedName("publishedDate")
    val publishedDate: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("imageLinks")
    val imageLinks: ImageLinkVO?,

    @SerializedName("categories")
    val categories: List<String>?
)
