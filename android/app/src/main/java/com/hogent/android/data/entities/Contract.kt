package com.hogent.android.data.entities

import com.squareup.moshi.JsonClass
import java.time.LocalDate


@JsonClass(generateAdapter = true)
data class Contract(
    val id: Int,
    val startDate: LocalDate,
    var endDate: LocalDate,
    val klantId : Int
)
