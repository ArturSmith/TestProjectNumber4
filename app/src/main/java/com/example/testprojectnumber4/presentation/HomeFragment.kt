package com.example.testprojectnumber4.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.testprojectnumber4.contracts.navigation
import com.example.testprojectnumber4.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("Unknown binding object")

    private val viewModel: HomeViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }


    private fun clickListeners() {
        binding.apply {
            logoutButton.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.deleteToken()
                    navigation().navigateToLoginScreen()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}