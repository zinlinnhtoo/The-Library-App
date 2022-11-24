package com.example.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class ItemVO(

    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfoVO?
) {
    fun toBookVO(): BookVO {
        return BookVO(
            title = volumeInfo?.title.orEmpty(),
            author = volumeInfo?.authors?.firstOrNull(),
            bookImage = volumeInfo?.imageLinks?.thumbnail,
            description = volumeInfo?.description.orEmpty(),
            bookWidth = null,
            bookHeight = null,
            updatedDate = volumeInfo?.publishedDate.orEmpty(),
            bookCategory = volumeInfo?.categories?.firstOrNull()
        )
    }
}
