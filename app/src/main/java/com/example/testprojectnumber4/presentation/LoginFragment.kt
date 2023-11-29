package com.example.testprojectnumber4.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.testprojectnumber4.contracts.navigation
import com.example.testprojectnumber4.data.entity.ScreenState
import com.example.testprojectnumber4.data.entity.TextFieldType
import com.example.testprojectnumber4.databinding.FragmentLoginBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("Unknown binding object")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        observers()
        textChangedListeners()
    }

    private fun textChangedListeners() {
        binding.apply {
            etLogin.doOnTextChanged { text, _, _, _ ->
                if (text?.isNotEmpty() == true) {
                    viewModel.setText(TextFieldType.LOGIN, text.toString().trim())
                }
            }
            etPassword.doOnTextChanged { text, _, _, _ ->
                if (text?.isNotEmpty() == true) {
                    viewModel.setText(TextFieldType.PASSWORD, text.toString().trim())
                }
            }
        }
    }

    private fun clickListeners() {
        binding.buttonLogin.setOnClickListener {
            lifecycleScope.launch {
                viewModel.login()
            }
        }
    }

    private fun observers() {
        lifecycleScope.launch {
            viewModel.screenState.collect {
                Log.d("UIState", it.toString())
                when (it) {
                    is ScreenState.Initial -> {
                        setUiState(
                            View.VISIBLE,
                            View.GONE,
                            View.INVISIBLE,
                            "",
                            View.VISIBLE,
                            View.INVISIBLE
                        )
                    }

                    is ScreenState.Loading -> {
                        setUiState(
                            View.INVISIBLE,
                            View.VISIBLE,
                            View.INVISIBLE,
                            "",
                            View.INVISIBLE,
                            View.INVISIBLE
                        )
                    }

                    is ScreenState.Success -> {
                        setUiState(
                            View.INVISIBLE,
                            View.INVISIBLE,
                            View.INVISIBLE,
                            "",
                            View.INVISIBLE,
                            View.VISIBLE
                        )
                    }

                    is ScreenState.Error -> {
                        val errorMessage = it.errorMessage
                        setUiState(
                            View.VISIBLE,
                            View.INVISIBLE,
                            View.VISIBLE,
                            errorMessage,
                            View.VISIBLE,
                            View.INVISIBLE
                        )
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.tfValidator.collect {
                binding.apply {
                    tilLogin.error = it.loginErrorText
                    tilPassword.error = it.passwordErrorText
                }
            }
        }
    }

    private fun setUiState(
        cardVisibility: Int,
        progressBarVisibility: Int,
        tvErrorVisibility: Int,
        errorMessage: String,
        buttonVisibility: Int,
        imageSuccessVisibility: Int
    ) {
        binding.apply {
            cardView.visibility = cardVisibility
            progressBar.visibility = progressBarVisibility
            tvError.visibility = tvErrorVisibility
            imageSuccess.visibility = imageSuccessVisibility
            tvError.text = errorMessage
            buttonLogin.visibility = buttonVisibility
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}