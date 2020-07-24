package com.example.pg_mvi4.layers.domain

import com.example.pg_mvi4.models.Intent
import com.example.pg_mvi4.models.XCountModelResult
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.xml.transform.Transformer

// transforms Intents to Results
class XCountModel(xCountDomainObj: XCountDomainObj) {
    val transformIntentToResult = ObservableTransformer<Intent, XCountModelResult> {
        it.map {
            when (it) {
                Intent.BumpXCount1 -> {
                    xCountDomainObj.xCount1++
                    XCountModelResult.newXCount1(xCountDomainObj.xCount1)
                }
                Intent.BumpXCount2 -> {
                    xCountDomainObj.xCount2++
                    XCountModelResult.newXCount2(xCountDomainObj.xCount2)
                }
                Intent.ToastSum -> {
                    val sum = xCountDomainObj.xCount1 + xCountDomainObj.xCount2
                    XCountModelResult.newSum(sum)
                }
            }
        }
    }
}