package com.example.pg_mvi4.layers.data_layer

import com.example.pg_mvi4.ENDPOINT_PRODUCTS
import com.example.pg_mvi4.models.ProductsResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    @GET(ENDPOINT_PRODUCTS)
    fun readProducts(): Observable<ProductsResponse>
}