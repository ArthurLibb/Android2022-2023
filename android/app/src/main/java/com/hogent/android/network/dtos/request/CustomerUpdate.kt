package com.hogent.android.network.dtos.request

import com.hogent.android.data.entities.ContactDetails
import com.hogent.android.data.entities.Course

class CustomerUpdate (
    var firstname : String,
    var name : String,
    var phoneNumber: String,
    var email: String,
    var opleiding: String?,
    var bedrijf: String?,
    var contactPersoon: ContactDetails?,
    var reserveContactpersoon: ContactDetails?
)