package com.example.thelibraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "books")
data class BookVO(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author: String?,

    @SerializedName("book_image")
    @ColumnInfo(name = "book_image")
    val bookImage: String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,

    @SerializedName("book_image_width")
    @ColumnInfo(name = "book_image_width")
    val bookWidth: Int?,

    @SerializedName("book_image_height")
    @ColumnInfo(name = "book_image_height")
    val bookHeight: Int?,

    @SerializedName("updated_date")
    @ColumnInfo(name = "updated_date")
    val updatedDate: String?,

    @ColumnInfo(name = "book_category")
    var bookCategory: String?
)
