package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.a963103033239757ba10504dc3857ddc7.databinding.FragmentSatelliteDetailBinding
import com.example.a963103033239757ba10504dc3857ddc7.domain.model.SatelliteDetailModel
import com.example.a963103033239757ba10504dc3857ddc7.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.uiState.collect {
                if (it.error != null) {
                    showError(it.error)
                } else if (it.data != null) {
                    binding.model = it.data as SatelliteDetailModel?
                }
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.uiStatePositions.collect {
                if (it.error != null) {
                    showError(it.error)
                } else if (it.data != null) {
                    binding.model?.lastPosition = it.data as String
                    binding.invalidateAll() // refresh UI
                }
            }
        }
    }

    private fun getSatelliteDetails(id: String) {
        viewModel.getSatelliteDetails(id)
    }

    private fun showError(error: String) {
        (activity as MainActivity?)?.showError(error)
    }

}