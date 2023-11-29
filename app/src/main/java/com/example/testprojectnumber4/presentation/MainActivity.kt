package com.example.testprojectnumber4.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.testprojectnumber4.R
import com.example.testprojectnumber4.contracts.NavigationContract
import com.example.testprojectnumber4.data.entity.AuthState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), NavigationContract {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            viewModel.authState.collect {
                when (it) {
                    AuthState.Authorized -> navigateToHomeScreen()
                    AuthState.NotAuthorized -> navigateToLoginScreen()
                }
            }
        }
    }

    override fun navigateToHomeScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, HomeFragment())
            .commit()
    }

    override fun navigateToLoginScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, LoginFragment())
            .commit()
    }
}
