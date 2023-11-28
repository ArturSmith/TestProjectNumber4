package com.example.testprojectnumber4.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprojectnumber4.data.AppRepositoryImpl
import com.example.testprojectnumber4.data.entity.ScreenState
import com.example.testprojectnumber4.data.entity.TextFieldType
import com.example.testprojectnumber4.data.entity.TextFieldsValidator
import com.example.testprojectnumber4.data.pojo.LoginRequest
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val appRepositoryImpl: AppRepositoryImpl
) : ViewModel() {

    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Initial)
    val screenState = _screenState.asStateFlow()

    private val _tfValidator = MutableStateFlow(TextFieldsValidator())
    val tfValidator = _tfValidator.asStateFlow()
    suspend fun login() {
        _tfValidator.apply {
            val login = value.loginText
            val password = value.passwordText

            viewModelScope.async {
                emit(
                    value.copy(
                        loginErrorText = validateLogin(login),
                        passwordErrorText = validatePassword(password)
                    )
                )
            }.await()

            val isLoginError = value.loginErrorText.isNotEmpty()
            val isPasswordError = value.passwordErrorText.isNotEmpty()

            if (isLoginError || isPasswordError) return

            _screenState.emit(ScreenState.Loading)
            val response = viewModelScope.async {
                appRepositoryImpl.login(LoginRequest(login, password))
            }.await()
            _screenState.emit(response)
        }
    }

    fun setText(tfType: TextFieldType, text: String) {
        viewModelScope.launch {
            _tfValidator.apply {
                when (tfType) {
                    TextFieldType.LOGIN -> {
                        validateLogin(text)
                        emit(value.copy(loginText = text, loginErrorText = validateLogin(text)))
                    }

                    TextFieldType.PASSWORD -> {
                        emit(
                            value.copy(
                                passwordText = text,
                                passwordErrorText = validatePassword(text)
                            )
                        )
                        validatePassword(text)
                    }
                }
            }
        }
    }

    private fun validateLogin(text: String): String {
        return when {
            text.isEmpty() -> {
                "Enter login"
            }

            else -> ""
        }
    }

    private fun validatePassword(text: String): String {
        return when {
            text.isEmpty() -> "Enter password"
            text.length < 5 -> "Password can not be shorter than 5 numbers"
            else -> ""
        }
    }
}