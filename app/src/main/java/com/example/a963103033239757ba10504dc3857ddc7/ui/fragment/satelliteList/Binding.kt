package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteList

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteDetail.SatelliteDetailModel
import com.example.a963103033239757ba10504dc3857ddc7.ui.model.satellite.SatelliteListModelItem

class Binding {
    companion object{
        @BindingAdapter("android:navigateToDetailFragment")
        @JvmStatic
        fun navigateToDetailFragment(view: ConstraintLayout, id: Int) {
            view.setOnClickListener {
                view.findNavController()
                    .navigate(
                        SatelliteListFragmentDirections.actionSatelliteListFragmentToSatelliteDetailFragment(
                            id
                        )
                    )
            }
        }
    }
}