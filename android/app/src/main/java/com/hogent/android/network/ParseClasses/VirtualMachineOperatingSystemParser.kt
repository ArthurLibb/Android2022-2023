package com.hogent.android.network.ParseClasses

import com.hogent.android.data.entities.OperatingSystem
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class VirtualMachineOperatingSystemParser {
    @FromJson
    fun fromJson(os : Int): OperatingSystem {
        return when(os){
            1 -> OperatingSystem.WINDOWS_10
            2 -> OperatingSystem.WINDOWS_SERVER2019
            3 -> OperatingSystem.KALI_LINUX
            4 -> OperatingSystem.UBUNTU_22_04
            5 -> OperatingSystem.FEDORA_36
            else -> OperatingSystem.FEDORA_35
        }
    }
    @ToJson
    fun toJson(os : OperatingSystem): Int{
        return os.ordinal
    }
}