package com.example.pg_mvi4.layers.data_layer

import com.example.pg_mvi4.BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// These should be moved into module when Dagger is implemented
val apiService = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build().create(ApiService::class.java)
val apiServiceWrapper = ApiServiceWrapper(apiService)

// This should be made a class when Dagger is implemented
object Repo: IApiServiceWrapper by apiServiceWrapper