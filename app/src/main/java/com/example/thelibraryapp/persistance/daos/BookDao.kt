package com.example.thelibraryapp.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thelibraryapp.data.vos.BookVO

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(bookList: List<BookVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleBook(book: BookVO?)

    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<BookVO>>

    @Query("DELETE FROM books")
    fun deleteAllBooks()
}