package com.example.testprojectnumber4.data

import android.content.Context
import android.util.Log
import com.example.testprojectnumber4.data.api.ApiFactory
import com.example.testprojectnumber4.data.entity.ScreenState
import com.example.testprojectnumber4.data.models.LoginRequest
import com.example.testprojectnumber4.data.models.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class AppRepositoryImpl(
    private val context: Context,
    private val apiFactory: ApiFactory
) {
    private val sharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    }
    private val sharedEditor = sharedPreferences.edit()


    suspend fun login(loginRequest: LoginRequest): ScreenState {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiFactory.apiService.login(loginRequest)
                if (response.token != null) {
                    saveToken(response.token.token)
                    ScreenState.Success
                } else {
                    ScreenState.Error("Wrong login or password, try again.")
                }
            } catch (e: Exception) {
                ScreenState.Error(e.message.toString())
            }
        }
    }

    private suspend fun saveToken(token: String) {
        withContext(Dispatchers.IO) {
            sharedEditor.putString(TOKEN_KEY, token)
            sharedEditor.apply()
        }
    }

    suspend fun deleteToken() {
        withContext(Dispatchers.IO) {
            sharedEditor.remove(TOKEN_KEY)
            sharedEditor.apply()
        }
    }

    suspend fun getToken(): String? = withContext(Dispatchers.IO) {
        val token = sharedPreferences.getString(TOKEN_KEY, null)
        token
    }


    private companion object {
        const val SHARED_PREFS = "sharedPrefs"
        const val TOKEN_KEY = "tokenKey"
    }


}