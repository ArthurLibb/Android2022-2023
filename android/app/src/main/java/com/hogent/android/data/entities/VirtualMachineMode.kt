package com.hogent.android.data.entities
import androidx.core.text.isDigitsOnly
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

enum class VirtualMachineMode {
    WAITING_APPROVEMENT,
    READY,
    RUNNING,
    PAUSED,
    STOPPED;

     fun NaarString(): String {
        return when(this){
            WAITING_APPROVEMENT -> "Wachten op goedkeuring"
            READY -> "Gereed"
            RUNNING -> "Running"
            PAUSED -> "Gepauzeerd"
            STOPPED -> "Gestopped"
        }
    }
}











