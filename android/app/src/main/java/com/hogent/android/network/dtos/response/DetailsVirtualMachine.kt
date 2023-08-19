package com.hogent.android.network.dtos.response

import com.hogent.android.data.entities.*
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DetailsVirtualMachine (
    val id :Int,
    val name : String,
    val mode : VirtualMachineMode,
    val hardware : HardWare,
    val operatingSystem: OperatingSystem,
    val contract : Contract,
    val backUp : Backup,
    val fysiekeServer : FysiekeServer,
    val vmConnection : Connection
)