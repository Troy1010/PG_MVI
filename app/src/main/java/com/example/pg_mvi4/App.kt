package com.example.pg_mvi4

import android.app.Application
import com.example.tmcommonkotlin.logz

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        logz("START")
    }
}