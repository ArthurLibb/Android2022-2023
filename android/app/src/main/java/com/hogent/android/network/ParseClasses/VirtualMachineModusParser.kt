package com.hogent.android.network.ParseClasses

import com.hogent.android.data.entities.VirtualMachineMode
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class VirtualMachineModusParser {

    @FromJson
    fun formJson (mode : Int): VirtualMachineMode {
        return when(mode){
            0 -> VirtualMachineMode.WAITING_APPROVEMENT
            1 -> VirtualMachineMode.READY
            2 -> VirtualMachineMode.RUNNING
            3 -> VirtualMachineMode.PAUSED
            else -> VirtualMachineMode.STOPPED}
        }

    @ToJson
    fun toJson (mode : VirtualMachineMode): Int {
        return mode.ordinal
    }
}
