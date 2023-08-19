package com.hogent.android.network

import com.hogent.android.network.ParseClasses.DateTimeParser
import com.hogent.android.network.ParseClasses.VirtualMachineBackupParser
import com.hogent.android.network.ParseClasses.VirtualMachineModusParser
import com.hogent.android.network.ParseClasses.VirtualMachineOperatingSystemParser
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*


class Config {
    companion object{
        private const val BASE_URL = "http://10.0.2.2:5000/api/"
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(VirtualMachineOperatingSystemParser())
            .add(VirtualMachineBackupParser())
            .add(DateTimeParser())
            .add(LocalDateAdapter())
            .add(VirtualMachineModusParser())
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .build();

        fun createRetrofit(endpoint : String): Retrofit{
            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL + endpoint)
                .client(okHttpClient)
                .build();
        }

    }
}

public class LocalDateAdapter : JsonAdapter<LocalDate>() {

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value.toString())
    }

    @FromJson
    override fun fromJson(reader: JsonReader): LocalDate? {
        if (reader.peek() == JsonReader.Token.NULL) {
            reader.nextNull<Unit>()
            return null
        }
        val dateString = reader.nextString()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return LocalDate.from(LocalDateTime.parse(dateString, formatter))
    }
}

