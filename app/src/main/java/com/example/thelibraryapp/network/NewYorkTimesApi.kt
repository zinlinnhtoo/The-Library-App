package com.example.thelibraryapp.network


import com.example.thelibraryapp.network.responses.BookListResponse
import com.example.thelibraryapp.network.responses.MoreBookListResponse
import com.example.thelibraryapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewYorkTimesApi {

    @GET(API_GET_OVERVIEW)
    fun getOverview(
        @Query(PARAM_API_KEY) apiKey: String = NYT_API_KEY
    ): Observable<BookListResponse>

    @GET(API_GET_BOOK_LIST_BY_LIST_NAME)
    fun getBookByListName(
        @Query(PARAM_API_KEY) apiKey: String = NYT_API_KEY,
        @Query(PARAM_LISTS) list: String
    ): Observable<MoreBookListResponse>
}