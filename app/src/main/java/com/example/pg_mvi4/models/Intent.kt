package com.example.pg_mvi4.models

sealed class Intent {
    object BumpXCount1:Intent()
    object BumpXCount2:Intent()
    object ToastSum:Intent()
}