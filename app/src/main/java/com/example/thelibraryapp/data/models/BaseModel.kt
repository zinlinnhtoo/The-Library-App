package com.example.thelibraryapp.data.models

import android.content.Context
import com.example.thelibraryapp.network.NewYorkTimesApi
import com.example.thelibraryapp.persistance.TheLibraryDatabase
import com.example.thelibraryapp.utils.BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {
    protected var mNewYorkTimesApi: NewYorkTimesApi
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

        mNewYorkTimesApi = retrofit.create(NewYorkTimesApi::class.java)
    }

    fun initDatabase(context: Context) {
        mTheLibraryDatabase = TheLibraryDatabase.getDBInstance(context)
    }
}