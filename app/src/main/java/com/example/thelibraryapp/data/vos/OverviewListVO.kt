package com.example.thelibraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.thelibraryapp.persistance.typeconverters.BookTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "overviews")
@TypeConverters(
    BookTypeConverter::class
)
data class OverviewListVO(

    @SerializedName("list_id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("list_name")
    @ColumnInfo(name = "list_name")
    val listName: String?,

    @SerializedName("list_name_encoded")
    @ColumnInfo(name = "list_name_encoded")
    val listNameEncoded: String?,

    @SerializedName("display_name")
    @ColumnInfo(name = "display_name")
    val displayName: String?,

    @SerializedName("books")
    @ColumnInfo(name = "books")
    val books: List<BookVO>?

)