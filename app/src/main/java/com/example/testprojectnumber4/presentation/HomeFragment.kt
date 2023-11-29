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
    private val adapter by lazy {
        PaymentsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        clickListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.payments.collect {
                adapter.submitList(it)
                if (it.isEmpty()) {
                    setUI(false, "No available payments")
                } else {
                    setUI(true, "")
                }
            }
        }
        lifecycleScope.launch {
            viewModel.loadPaymentsError.collect {
                if (it.isNotEmpty()) {
                    setUI(false, it)
                } else {
                    setUI(true, "")
                }
            }
        }
    }

    private fun setUI(isRecyclerVisible: Boolean, text: String) {
        binding.apply {
            if (isRecyclerVisible) {
                loadListInfo.visibility = View.GONE
                loadListInfo.text = ""
                recycler.visibility = View.VISIBLE
            } else {
                loadListInfo.visibility = View.VISIBLE
                loadListInfo.text = text
                recycler.visibility = View.GONE
            }
        }
    }


    private fun clickListeners() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                lifecycleScope.launch {
                    viewModel.logout()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}