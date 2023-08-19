package com.hogent.android.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Project(
   val id : Int,
   val name : String,
   val virtualMachines : List<VirtualMachine>
    )


