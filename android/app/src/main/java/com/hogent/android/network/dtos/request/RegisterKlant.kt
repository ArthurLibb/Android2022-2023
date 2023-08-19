package com.hogent.android.network.dtos.request

class RegisterKlant (
    val firstname : String,
    val lastname : String,
    val password : String,
    val email : String,
    val phoneNumber : String
){
    //debug
    override fun toString(): String {
        return "RegisterKlant(firstname='$firstname', lastname='$lastname', password='$password', email='$email', phoneNumber='$phoneNumber')"
    }
}