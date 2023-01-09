package com.example.kotlinapp.presentation.view.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.domain.auth.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authInteractor: AuthInteractor): ViewModel() {
    private val _nav = MutableLiveData<Unit?>()
    val nav: LiveData<Unit?> = _nav

    fun loginUser(userName: String, userPassword: String){
//        viewModelScope.launch(Dispatchers.IO) {
//            authInteractor.loginUser(userName, userPassword)
//            _nav.postValue(Unit)  //используем метод postValue для работы не на Main потоке, т.к LiveData работает только на Main-потоке
//        }


//        viewModelScope.launch(Dispatchers.Main) {
//            val job = launch {
//                authInteractor.loginUser(userName, userPassword)
//                _nav.value = Unit
//            }
//            delay(1000)
//            job.cancel()  // намеренное закрытие корутины
//        }

            val coroutineExceptionHandler = CoroutineExceptionHandler{_, exception ->
                Log.w("exceptionHandlerCalled", exception.toString())
            }

            viewModelScope.launch(CoroutineName("with exception") + coroutineExceptionHandler + Dispatchers.Main) {
                try {
                    launch { // если падает дочерняя корутина, то падает и родительская, поэтому используется ExceptionHandler, чтобы ловилась ошибка
                        authInteractor.loginUser(userName, userPassword)
                        _nav.value = Unit
                    }

                }catch (e: Exception){
                    Log.w("exception","loginUser FAILED")
                }

            }
        }
}
