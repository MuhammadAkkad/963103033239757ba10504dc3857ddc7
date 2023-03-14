package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.a963103033239757ba10504dc3857ddc7.databinding.FragmentSatelliteDetailBinding


class SatelliteDetailFragment : Fragment() {

    private var _binding: FragmentSatelliteDetailBinding? = null

    private val binding get() = _binding!!

    private val args by navArgs<SatelliteDetailFragmentArgs>()

    companion object {
        fun newInstance() = SatelliteDetailFragment()
    }

    private lateinit var viewModel: SatelliteDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSatelliteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}