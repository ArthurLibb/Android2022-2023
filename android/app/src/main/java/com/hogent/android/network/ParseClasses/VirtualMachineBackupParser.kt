package com.hogent.android.network.ParseClasses


import com.hogent.android.data.entities.BackupType
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class VirtualMachineBackupParser {
    @FromJson
    fun fromJson(type : Int) : BackupType {
        return when(type){
            0-> BackupType.CUSTOM
            1 -> BackupType.DAILY
            2 -> BackupType.WEEKLY
            else -> BackupType.MONTHLY
        }
    }
    @ToJson
    fun ToJson(type : BackupType): Int{
        return type.ordinal
    }
}