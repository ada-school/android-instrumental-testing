package org.adaschool.instrumentaltesting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.adaschool.instrumentaltesting.R

/**
 * @author Santiago Carrillo
 * 10/06/21.
 */

const val SUCCESS_RESPONSE_INT = 0

class LoginFragmentViewModel : ViewModel() {

    val successLiveData = MutableLiveData<Int>()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Thread.sleep(5_000)
            if (email == "test@mail.com") {
                successLiveData.postValue(SUCCESS_RESPONSE_INT)
            } else {
                successLiveData.postValue(R.string.authentication_error_message)
            }
        }
    }


}