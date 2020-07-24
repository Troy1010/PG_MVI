package com.example.pg_mvi4

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

const val LOG_TAG = "TMLog"

fun shortClassName(className:String):String {
    val periodMatches = Regex("""\..""").findAll(className)
    if (periodMatches.count()==0)
        return className
    val periodPos = periodMatches.last().range.last
    return className.substring(periodPos)
}

fun TMLog (msg:String?, bClass:Boolean=false, bMethod:Boolean=false) {
    var msgZ = msg ?: ""
    val level = if(msg==null) 2 else 1
    val throwable = Throwable()
    if (bClass)
        msgZ = "${shortClassName(throwable.stackTrace[level].className)}`"+msgZ
    if (bMethod)
        msgZ = "${shortClassName(throwable.stackTrace[level].className)}`"+msgZ
    Log.d(LOG_TAG, "TM`$msgZ")
}

fun logz (msg:String) {
    TMLog(msg)
}

fun <T: ViewModel> vmFactoryFactory(constructor:()->T) : ViewModelProvider.Factory {
    return object: ViewModelProvider.Factory {
        override fun <Y : ViewModel?> create(modelClass: Class<Y>): Y {
            @Suppress("UNCHECKED_CAST")
            return constructor() as Y
        }
    }
}

fun Context.easyToast(msg:String, lengthID:Int= Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, lengthID).show()
}

fun <T> convertRXToLiveData (observable: Observable<T>): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(Flowable.fromObservable(observable, BackpressureStrategy.DROP))
}

fun <T> Observable<T>.toLiveData(): LiveData<T> {
    return convertRXToLiveData(this)
}
