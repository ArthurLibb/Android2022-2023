package com.hogent.android.network.ParseClasses

import android.icu.text.MessageFormat.format
import android.text.format.DateFormat.format
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.lang.String.format
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeParser : JsonAdapter<LocalDate>() {
    @FromJson
    override fun fromJson(reader: JsonReader): LocalDate? {
        val dateString = reader.nextString().split("T")[0]
        val outputFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val correcteParse = LocalDate.parse(dateString,outputFormatter)
        return correcteParse
    }
    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        if (value != null) {
            val zonedDateTime = value.atStartOfDay()
            writer.value(value.toString())
        } else {
            writer.nullValue()
        }
    }

}