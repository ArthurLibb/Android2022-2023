package com.hogent.android.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FysiekeServer (
    val id : Int,
    val name : String,
    val serverAddress : String
)
