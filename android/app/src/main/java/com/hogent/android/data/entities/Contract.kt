package com.hogent.android.data.entities

import com.squareup.moshi.JsonClass
import java.time.LocalDate


@JsonClass(generateAdapter = true)
data class Contract(
    val startDate: LocalDate,
    var endDate: LocalDate,
    val id: Int,
    val vmId : Int,
    val customerId: Int
)
