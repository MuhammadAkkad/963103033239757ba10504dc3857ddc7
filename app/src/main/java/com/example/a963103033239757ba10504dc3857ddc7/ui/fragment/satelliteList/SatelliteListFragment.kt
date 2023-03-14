package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteList

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a963103033239757ba10504dc3857ddc7.databinding.FragmentSatelliteListBinding
import com.example.a963103033239757ba10504dc3857ddc7.ui.model.satellite.SatelliteListModelItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SatelliteListFragment : Fragment() {

    private var _binding: FragmentSatelliteListBinding? = null

    private val binding get() = _binding!!

    lateinit var adapter: SatelliteAdapter

    companion object {
        fun newInstance() = SatelliteListFragment()
    }

    private lateinit var viewModel: SatilliteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSatelliteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupEditTextSearch()
    }

    private fun setupList() {
        val list = ArrayList<SatelliteListModelItem>()
        list.add(SatelliteListModelItem(111, "One-1", false))
        list.add(SatelliteListModelItem(112, "Two-2", true))
        list.add(SatelliteListModelItem(113, "Three-3", true))
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
        val delay = 400L

        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                debounceJob?.cancel()
                debounceJob = this@SatelliteListFragment.viewLifecycleOwner.lifecycle.coroutineScope
                    .launch(Dispatchers.Main) {
                        delay(delay)
                        adapter.filter.filter(text?.toString()?.trim())
                    }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }
}