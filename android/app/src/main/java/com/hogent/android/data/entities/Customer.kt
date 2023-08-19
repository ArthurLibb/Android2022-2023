package com.hogent.android.data.entities

import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Customer(
    val name: String,
    val firstName: String,
    val phoneNumber: String,
    val email: String,
    val opleiding : String? = null,
    var bedrijf: String? = null,
    var contactPersoon:  ContactDetails? ,
    var reserveContactPersoon: ContactDetails?,
    val id: Int = 0
)

@JsonClass(generateAdapter = true)
data class ContactDetails(
    var phoneNumber: String? = "",
    var email: String? = "",
    var firstname : String? = "",
    var lastname: String? = ""
)


enum class Course{
    NONE,
    TOEGEPASTE_INFORMATICA,
    AGRO_EN_BIOTECHNOLOGIE,
    BIOMEDISCHE_LABORATORIUMTECHNOLOGIE,
    CHEMIE,
    DIGITAL_DESIGN_AND_DEVELOPMENT,
    ELEKTROMECHANICA,
}



