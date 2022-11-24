package com.example.thelibraryapp.network

import com.example.thelibraryapp.network.responses.GoogleBookListResponse
import com.example.thelibraryapp.utils.API_GET_GOOGLE_BOOK_VOLUME
import com.example.thelibraryapp.utils.PARAM_Q
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBookApi {
    @GET(API_GET_GOOGLE_BOOK_VOLUME)
    fun searchGoogleBook(
        @Query(PARAM_Q) q: String
    ): Observable<GoogleBookListResponse>
}