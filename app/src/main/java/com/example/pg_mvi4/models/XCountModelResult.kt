package com.example.pg_mvi4.models

sealed class XCountModelResult {
    data class newXCount1(val xCount1:Int): XCountModelResult()
    data class newXCount2(val xCount2: Int): XCountModelResult()
}