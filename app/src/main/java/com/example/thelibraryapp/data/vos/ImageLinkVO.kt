package com.example.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class ImageLinkVO(
    @SerializedName("smallThumbnail")
    val smallThumbnail: String?,

    @SerializedName("thumbnail")
    val thumbnail: String?
)
