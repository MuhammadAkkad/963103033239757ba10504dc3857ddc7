package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a963103033239757ba10504dc3857ddc7.domain.model.SatelliteDetailModel
import com.example.a963103033239757ba10504dc3857ddc7.domain.repository.SatelliteListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(private val repository: SatelliteListRepository) :
    ViewModel() {

    val liveData = MutableLiveData<SatelliteDetailModel>()

    fun getSatelliteDetails(id: String) {
        val data = repository.getSatelliteDetails(id)
        data?.let {
            liveData.postValue(it)
        }
    }
}