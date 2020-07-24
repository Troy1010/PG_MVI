package com.example.pg_mvi4.layers.data_layer

import com.example.pg_mvi4.models.Product
import io.reactivex.rxjava3.core.Observable

interface IApiServiceWrapper {
    val apiService: ApiService
    fun readProducts(): Observable<List<Product>>
}