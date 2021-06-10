package org.adaschool.instrumentaltesting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Santiago Carrillo
 * 10/06/21.
 */
class LoginFragmentViewModel : ViewModel() {

    val successLiveData = MutableLiveData<Boolean>()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Thread.sleep(5_000)
            if (email == "test@mail.com") {
                successLiveData.postValue(true)
            } else {
                successLiveData.postValue(false)
            }
        }
    }


}