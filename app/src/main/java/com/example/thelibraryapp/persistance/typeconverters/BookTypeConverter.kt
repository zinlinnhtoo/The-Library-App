package com.example.thelibraryapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.thelibraryapp.data.vos.BookVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookTypeConverter {
    @TypeConverter
    fun toString(bookList: List<BookVO>?): String {
        return Gson().toJson(bookList)
    }

    @TypeConverter
    fun toBookList(bookListJsonString: String): List<BookVO>? {
        val bookListType = object : TypeToken<List<BookVO>?>() {}.type
        return Gson().fromJson(bookListJsonString, bookListType)
    }
}