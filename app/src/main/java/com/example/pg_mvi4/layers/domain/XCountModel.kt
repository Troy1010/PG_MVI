package com.example.pg_mvi4.layers.domain

import com.example.pg_mvi4.layers.data_layer.Repo
import com.example.pg_mvi4.models.Intent
import com.example.pg_mvi4.models.XCountModelResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.xml.transform.Transformer

// transforms Intents to Results
class XCountModel(xCountDomainObj: XCountDomainObj) {
    val transformIntentToResult = ObservableTransformer<Intent, XCountModelResult> {
        it.publish { selector ->
            Observable.merge(
                selector.ofType(Intent.BumpXCount1::class.java).map {
                    xCountDomainObj.xCount1++
                    XCountModelResult.newXCount1(xCountDomainObj.xCount1)
                },
                selector.ofType(Intent.BumpXCount2::class.java).map {
                    xCountDomainObj.xCount2++
                    XCountModelResult.newXCount2(xCountDomainObj.xCount2)
                },
                selector.ofType(Intent.ToastSum::class.java).map {
                    val sum = xCountDomainObj.xCount1 + xCountDomainObj.xCount2
                    XCountModelResult.newSum(sum)
                },
                selector.ofType(Intent.ToastProductCount::class.java).flatMap {
                    Repo.readProducts().map { XCountModelResult.newProducts(it) }
                }
            )
        }
    }
}