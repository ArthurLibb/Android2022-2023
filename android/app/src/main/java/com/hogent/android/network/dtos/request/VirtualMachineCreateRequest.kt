package com.hogent.android.network.dtos.request

import com.hogent.android.data.entities.BackupType
import com.hogent.android.data.entities.HardWare
import com.hogent.android.data.entities.OperatingSystem
import java.time.LocalDate

class VirtualMachineCreateRequest(val virtualMachine : VirtualMachineDtoCreate , val customerId : Int) {
}

class BackUpDtoCreateVm(val type: BackupType?, val lastBackUp: LocalDate?)
class VirtualMachineDtoCreate(val name : String, val hardware : HardWare,
                              val operatingSystem : OperatingSystem, val backUp : BackUpDtoCreateVm,
                              val projectId : Int , val start : LocalDate, val end : LocalDate)