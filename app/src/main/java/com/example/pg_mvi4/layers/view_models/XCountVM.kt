package com.example.pg_mvi4.layers.view_models

import androidx.lifecycle.ViewModel
import com.example.pg_mvi4.models.Intent
import io.reactivex.rxjava3.subjects.PublishSubject

class XCountVM: ViewModel() {
    val intentStreamStream = PublishSubject.create<PublishSubject<Intent>>()
    fun mergeIntentStream(intentStream: PublishSubject<Intent>) {
        intentStreamStream.onNext(intentStream)
    }
    val eventStream = intentStreamStream.switchMap { it }
//        .
//    val stateStream
}