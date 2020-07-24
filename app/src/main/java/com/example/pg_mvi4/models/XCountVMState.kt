package com.example.pg_mvi4.models

import com.example.pg_mvi4.layers.domain.XCountDomainObj

data class XCountVMState (
    val xcount1: String = "",
    val xcount2: String = ""
) {
    constructor(xCountDomainObj: XCountDomainObj) : this(
        xcount1 = xCountDomainObj.xCount1.toString(),
        xcount2 = xCountDomainObj.xCount2.toString()
    )
}