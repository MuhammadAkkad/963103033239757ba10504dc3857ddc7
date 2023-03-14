package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satellite.SatelliteListModelItem
import com.example.a963103033239757ba10504dc3857ddc7.domain.repository.SatelliteListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SatelliteListViewModel @Inject constructor(private val repository: SatelliteListRepository) :
    ViewModel() {

    val data = MutableLiveData<ArrayList<SatelliteListModelItem>>()

    fun getList() {
        val list = repository.getSatelliteList()
        data.postValue(list as ArrayList<SatelliteListModelItem>?)
    }


}