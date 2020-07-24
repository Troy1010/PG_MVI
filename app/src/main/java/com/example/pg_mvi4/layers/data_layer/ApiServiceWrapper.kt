package com.example.pg_mvi4.layers.data_layer

import com.example.pg_mvi4.models.Product
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class ApiServiceWrapper(override val apiService: ApiService) : IApiServiceWrapper {
    override fun readProducts(): Observable<List<Product>> {
        return apiService.readProducts().map { it.data }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}