package com.hogent.android.data.entities

import androidx.core.text.isDigitsOnly

enum class OperatingSystem {
    WINDOWS_10,
    WINDOWS_SERVER2019,
    KALI_LINUX,
    UBUNTU_22_04,
    FEDORA_36,
    FEDORA_35;

    fun NaarString(): String{
        return when(this){
            WINDOWS_10 -> "Windows 10"
            WINDOWS_SERVER2019 -> "Windows server 2019"
            KALI_LINUX -> "Kali Linux"
            UBUNTU_22_04 -> "Ubuntu 22 04"
            FEDORA_36 -> "Fedora 36"
            FEDORA_35 -> "Fedora 35"
        }
    }
}