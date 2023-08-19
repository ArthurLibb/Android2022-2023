package com.hogent.android.data.repositories

import com.hogent.android.data.entities.Customer
import com.hogent.android.network.dtos.request.CustomerUpdate
import com.hogent.android.network.dtos.response.CustomerEditedReponse
import com.hogent.android.network.services.CustomerApi.customerService

class CustomerRepository {

    suspend fun updateCustomer(id: Int, customer: CustomerUpdate): CustomerEditedReponse? {
        val reponse = customerService.updateCustomer(id, customer)
        if(reponse.isSuccessful){
            return reponse.body()
        }
        return null
    }
}