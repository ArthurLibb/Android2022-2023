package com.hogent.android.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HardWare(
    val memory: Int,
    val storage: Int,
    val amount_vCPU: Int
)