package com.hogent.android.ui.klant

import android.text.Editable
import android.view.View
import androidx.lifecycle.*
import com.hogent.android.data.entities.ContactDetails
import com.hogent.android.data.entities.Customer
import com.hogent.android.data.repositories.CustomerRepository
import com.hogent.android.network.dtos.request.CustomerUpdate
import com.hogent.android.ui.components.forms.ContactOne
import com.hogent.android.ui.components.forms.ContactTwo
import com.hogent.android.ui.components.forms.CustomerContactEditForm
import com.hogent.android.util.AuthenticationManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class CustomerViewModel (private val repo: CustomerRepository) : ViewModel() {

    private val _form = MutableLiveData(CustomerContactEditForm())
    private val _klant = MutableLiveData<Customer>()

    val inEditMode = MutableLiveData(false)


    private val _errorToast = MutableLiveData<Boolean>()
    private val _successToast = MutableLiveData<Boolean>()
    private val _failsafeRedirect = MutableLiveData(false)


    val klant: LiveData<Customer>
        get() = _klant
    val success: LiveData<Boolean>
        get() = _successToast
    val error: LiveData<Boolean>
        get() = _errorToast
    val failsafeRedirect : LiveData<Boolean>
        get() = _failsafeRedirect

    fun setEmail(contactPerson: Int, text: Editable) {
        if (contactPerson == 1) {
            val contact = _form.value!!.contact1
            _form.postValue(
                CustomerContactEditForm(
                    ContactOne(
                        text.toString(),
                        contact.phone,
                        contact.fullName
                    ), _form.value!!.contact2
                )
            );

        } else if (contactPerson == 2) {
            val contact = _form.value!!.contact2
            _form.postValue(
                CustomerContactEditForm(
                    _form.value!!.contact1,
                    ContactTwo(text.toString(), contact.phone, contact.fullName)
                )
            );
        }
    }

    fun setPhone(contactPerson: Int, text: Editable) {

        if (contactPerson == 1) {
            val contact = _form.value!!.contact1
            _form.postValue(
                CustomerContactEditForm(
                    ContactOne(
                        contact.email,
                        text.toString(),
                        contact.fullName
                    ), _form.value!!.contact2
                )
            );
        } else if (contactPerson == 2) {
            val contact = _form.value!!.contact2
            _form.postValue(
                CustomerContactEditForm(
                    _form.value!!.contact1,
                    ContactTwo(contact.email, text.toString(), contact.fullName)
                )
            );
        }
    }

    fun setFullName(contactPerson: Int, text: Editable) {

        if (contactPerson == 1) {
            val contact = _form.value!!.contact1
            _form.postValue(
                CustomerContactEditForm(
                    ContactOne(
                        contact.email,
                        contact.phone,
                        text.toString()
                    ), _form.value!!.contact2
                )
            );
        } else if (contactPerson == 2) {
            val contact = _form.value!!.contact2
            _form.postValue(
                CustomerContactEditForm(
                    _form.value!!.contact1,
                    ContactTwo(contact.email, contact.phone, text.toString())
                )
            );
        }
    }

    fun onEditButtonPressed() {
        inEditMode.postValue(true)
        val contactps1 = klant.value!!.contactPersoon
        val contact2 = klant.value!!.reserveContactPersoon
        var contactOne = ContactOne("", "", "")
        var contactTwo = ContactTwo("", "", "")
        Timber.d(contactps1.toString())

        if (contactps1 != null) {
            val phone = contactps1.phoneNumber ?: ""
            val email = contactps1.email ?: ""
            contactOne = ContactOne(
                email,
                phone,
                contactps1.firstname + " " + contactps1.lastname
            );
            Timber.d("Contactps1: %s", contactOne.toString())
        }
        if (contactTwo != null){
            val phone = contact2?.phoneNumber ?: ""
            val email = contact2?.email ?: ""
            contactTwo = ContactTwo(
                email,
                phone,
                contact2?.firstname + " " + contact2?.lastname
            );
        }

        _form.postValue(CustomerContactEditForm(contactOne, contactTwo));

    }

    fun onCancelButtonPressed() {
        _form.postValue(CustomerContactEditForm())
        inEditMode.postValue(false)
    }

    fun onSubmitButtonPressed() {
        if (_form.value!!.isValid()) {
            Timber.d("VALID")
            inEditMode.postValue(false);
            _successToast.postValue(true);
            persistCustomer();
        } else {
            Timber.d("INVALID")
            Timber.d(_form.value!!.getError())
            _errorToast.postValue(true);
        }
    }

    fun getVisibleId(): Int {
        return View.VISIBLE;
    }

    fun getInvisibleId(): Int {
        return View.INVISIBLE
    }

    private fun persistCustomer() {
        val customer: Customer = klant!!.value!!.copy()
        val updatedCustomer = CustomerUpdate(customer.firstName, customer.name, customer.email,
            customer.phoneNumber, customer.bedrijf, customer.opleiding, customer.contactPersoon, customer.reserveContactPersoon )

        val contactDetails = ContactDetails(
            _form.value!!.contact1.phone,
            _form.value!!.contact1.email,
            _form.value!!.contact1.fullName.split(" ")[0],
            _form.value!!.contact1.fullName.substringAfter(" ")
        );
        customer.contactPersoon = contactDetails

        viewModelScope.launch(Dispatchers.Main) {
            Timber.d("Sending to backend ID: %d  and customer: %s", customer.id, customer.toString())
            repo.updateCustomer(customer.id, updatedCustomer)
            _klant.postValue(customer!!);
        }
    }


    fun doneErrorToast() {
        _errorToast.postValue(false);
    }

    fun doneSuccessToast() {
        _successToast.postValue(false);
    }

    fun getError(): String? {
        return _form.value!!.getError();
    }

    fun doneNavigation() {
        _failsafeRedirect.postValue(false)
    }


    init {
        if(!AuthenticationManager.loggedIn()){
            _failsafeRedirect.postValue(true)
        }else{
            _klant.postValue(AuthenticationManager.getCustomer())
            Timber.d(_klant.toString())
        }
    }
}

