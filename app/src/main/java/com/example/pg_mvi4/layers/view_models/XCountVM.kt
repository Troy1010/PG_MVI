package com.example.pg_mvi4.layers.view_models

import androidx.lifecycle.ViewModel
import com.example.pg_mvi4.layers.domain.XCountDomainObj
import com.example.pg_mvi4.layers.domain.XCountModel
import com.example.pg_mvi4.models.Intent
import com.example.pg_mvi4.models.XCountModelResult
import com.example.pg_mvi4.models.XCountVMState
import com.example.pg_mvi4.toLiveData
import io.reactivex.rxjava3.subjects.PublishSubject

class XCountVM(xCountDomainObj:XCountDomainObj): ViewModel() {
    private val reducer = { previousState: XCountVMState, result: XCountModelResult ->
        when (result) {
            is XCountModelResult.newXCount1 -> {
                previousState.copy(
                    xcount1 = result.xCount1.toString()
                )
            }
            is XCountModelResult.newXCount2 -> {
                previousState.copy(
                    xcount2 = result.xCount2.toString()
                )
            }
            else -> previousState
        }
    }
    private val intentStreamStream = PublishSubject.create<PublishSubject<Intent>>()
    fun mergeIntentStream(intentStream: PublishSubject<Intent>) {
        intentStreamStream.onNext(intentStream)
    }
    val eventStream = intentStreamStream.switchMap { it }
        .compose(XCountModel(xCountDomainObj).transformIntentToResult)
        .publish().refCount()
    val stateStream = eventStream
        .scan(XCountVMState(xCountDomainObj), reducer)
        .distinctUntilChanged()
        .replay(1).autoConnect()
        .toLiveData()
    init {
        eventStream.subscribe()
        stateStream.observeForever { }
    }
}