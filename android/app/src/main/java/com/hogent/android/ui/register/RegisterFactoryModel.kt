package com.hogent.android.ui.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hogent.android.data.repositories.LoginRepository

class RegisterFactoryModel(private val repo: LoginRepository, private val app : Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){
                return RegisterViewModel(repo, app) as T
        }
        throw IllegalArgumentException("unknown class")
    }
}