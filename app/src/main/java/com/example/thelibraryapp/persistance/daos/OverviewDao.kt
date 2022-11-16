package com.example.thelibraryapp.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thelibraryapp.data.vos.BookVO
import com.example.thelibraryapp.data.vos.OverviewListVO

@Dao
interface OverviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOverview(overviewList: List<OverviewListVO>)

    @Query("SELECT * FROM overviews")
    fun getAllOverviewList(): LiveData<List<OverviewListVO>>

    @Query("SELECT * FROM overviews")
    fun getAllOverviewListOneTime(): List<OverviewListVO>

    @Query("DELETE FROM overviews")
    fun deleteAllOverviewList()
}