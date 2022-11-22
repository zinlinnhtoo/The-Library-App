package com.example.thelibraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.thelibraryapp.persistance.typeconverters.BookTypeConverter

@Entity(tableName = "shelves")
@TypeConverters(
    BookTypeConverter::class
)
data class ShelfVO(

//    @PrimaryKey(autoGenerate = true)
//    val id: Int? = 0,

//    @ColumnInfo(name = "title")
    @PrimaryKey
    var title: String,

    @ColumnInfo(name = "books")
    val books: MutableList<BookVO>?
)
