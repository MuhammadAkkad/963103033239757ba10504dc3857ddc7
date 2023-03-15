package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.a963103033239757ba10504dc3857ddc7.databinding.FragmentSatelliteDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class SatelliteDetailFragment : Fragment() {

    private var _binding: FragmentSatelliteDetailBinding? = null

    private val binding get() = _binding!!

    private val args by navArgs<SatelliteDetailFragmentArgs>()

    private val viewModel: SatelliteDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSatelliteDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        args.let {
            initializeObservers()
            getSatelliteDetails(it.id.toString())
        }
    }

    private fun initializeObservers() {
        viewModel.satelliteLiveData.observe(viewLifecycleOwner) {
            binding.model = it
        }

        viewModel.positionLiveData.observe(viewLifecycleOwner) {
            binding.model?.lastPosition = it
            binding.invalidateAll() // refresh UI
        }
    }

    private fun getSatelliteDetails(id: String) {
        viewModel.getSatelliteDetails(id)
    }

}