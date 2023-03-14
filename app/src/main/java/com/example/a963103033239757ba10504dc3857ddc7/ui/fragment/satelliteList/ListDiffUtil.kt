package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteList

import androidx.recyclerview.widget.DiffUtil
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satellite.SatelliteListModelItem


class ListDiffUtil : DiffUtil.ItemCallback<SatelliteListModelItem>() {

    override fun areItemsTheSame(
        oldItem: SatelliteListModelItem,
        newItem: SatelliteListModelItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: SatelliteListModelItem,
        newItem: SatelliteListModelItem
    ): Boolean {
        return oldItem.id == newItem.id
                && oldItem.name == newItem.name
                && oldItem.active == newItem.active
    }

}