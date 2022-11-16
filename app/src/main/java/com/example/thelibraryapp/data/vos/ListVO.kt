package com.example.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class ListVO(

    @SerializedName("list_id")
    val id: Int?,

    @SerializedName("list_name")
    val listName: String?,

    @SerializedName("list_name_encoded")
    val listNameEncoded: String?,

    @SerializedName("display_name")
    val displayName: String?,

    @SerializedName("books")
    val books: List<BookVO>

)