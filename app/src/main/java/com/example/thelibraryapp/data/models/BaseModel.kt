package com.example.thelibraryapp.data.models

import android.content.Context
import com.example.thelibraryapp.network.GoogleBookApi
import com.example.thelibraryapp.network.NewYorkTimesApi
import com.example.thelibraryapp.persistance.TheLibraryDatabase
import com.example.thelibraryapp.utils.BASE_URL
import com.example.thelibraryapp.utils.BASE_URL_GOOGLE_BOOK
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

abstract class BaseModel {
    protected var mNewYorkTimesApi: NewYorkTimesApi
    protected var mGoogleBookApi: GoogleBookApi
    protected var mTheLibraryDatabase: TheLibraryDatabase? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val googleBookRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_GOOGLE_BOOK)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        mNewYorkTimesApi = retrofit.create(NewYorkTimesApi::class.java)
        mGoogleBookApi = googleBookRetrofit.create(GoogleBookApi::class.java)
    }

    fun initDatabase(context: Context) {
        mTheLibraryDatabase = TheLibraryDatabase.getDBInstance(context)
    }
}