package com.example.thelibraryapp

import android.app.Application
import com.example.thelibraryapp.data.models.BookModelImpl

class TheLibraryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        BookModelImpl.initDatabase(applicationContext)
    }
}