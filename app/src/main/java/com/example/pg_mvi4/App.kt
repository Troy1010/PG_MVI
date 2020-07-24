package com.example.pg_mvi4

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        logz("START")
    }
}