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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satellite.SatelliteListModelItem
import com.example.a963103033239757ba10504dc3857ddc7.databinding.FragmentSatelliteListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteListFragment : Fragment() {

    private var _binding: FragmentSatelliteListBinding? = null

    private val binding get() = _binding!!

    lateinit var adapter: SatelliteAdapter

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
        getSatelliteList()
    }

    private fun getSatelliteList() {
        viewModel.getList()
        viewModel.data.observe(viewLifecycleOwner) {
            setupList(it)
        }
    }

    private fun setupList(list: ArrayList<SatelliteListModelItem>) {
        adapter = SatelliteAdapter(list)
        binding.satelliteListRv.adapter = adapter
        binding.satelliteListRv.layoutManager = LinearLayoutManager(requireContext())
        binding.satelliteListRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.HORIZONTAL
            )
        )
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
                            adapter.filter.filter(text.toString().trim())
                        }
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }
}