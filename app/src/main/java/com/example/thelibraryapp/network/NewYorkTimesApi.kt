package com.example.thelibraryapp.network


import com.example.thelibraryapp.network.responses.BookListResponse
import com.example.thelibraryapp.utils.API_GET_OVERVIEW
import com.example.thelibraryapp.utils.NYT_API_KEY
import com.example.thelibraryapp.utils.PARAM_API_KEY
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewYorkTimesApi {

    @GET(API_GET_OVERVIEW)
    fun getOverview(
        @Query(PARAM_API_KEY) apiKey: String = NYT_API_KEY
    ): Observable<BookListResponse>

}