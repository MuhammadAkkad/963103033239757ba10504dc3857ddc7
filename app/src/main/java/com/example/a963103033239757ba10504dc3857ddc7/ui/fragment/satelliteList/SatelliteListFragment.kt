package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satellite.SatelliteListModelItem
import com.example.a963103033239757ba10504dc3857ddc7.databinding.FragmentSatelliteListBinding
import com.example.a963103033239757ba10504dc3857ddc7.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteListFragment : Fragment() {

    private var _binding: FragmentSatelliteListBinding? = null

    private val binding get() = _binding!!

    private var adapter: SatelliteAdapter? = null

    private val viewModel: SatelliteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSatelliteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupEditTextSearch()
        setupObservers()
        getSatelliteList()
    }

    private fun setupObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.uiState.collect {
                showLoading(it.isLoading)
                if (it.data != null) {
                    setupList(it.data as ArrayList<SatelliteListModelItem>)
                } else if (it.error != null) {
                    showError(it.error)
                }
            }
        }
    }

    private fun showError(error: String) {
        (activity as MainActivity?)?.showError(error)
    }

    private fun getSatelliteList() {
        viewModel.getList()
    }

    private fun showLoading(loading: Boolean) {
        binding.showLoading = loading
        binding.invalidateAll()
    }

    private fun setupList(list: ArrayList<SatelliteListModelItem>) {
        adapter = SatelliteAdapter(list)
        binding.satelliteListRv.adapter = adapter
        binding.satelliteListRv.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupEditTextSearch() {
        var debounceJob: Job? = null
        val delay = 500L

        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                if (text?.trim()?.isNotEmpty() == true) {
                    debounceJob?.cancel()
                    debounceJob = viewLifecycleOwner.lifecycle.coroutineScope
                        .launch(Dispatchers.Main) {
                            delay(delay)
                            adapter?.filter?.filter(text.toString().trim())
                        }
                }

            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }
}