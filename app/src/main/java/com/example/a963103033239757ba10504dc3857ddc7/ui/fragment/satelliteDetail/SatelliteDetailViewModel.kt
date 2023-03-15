package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel
import com.example.a963103033239757ba10504dc3857ddc7.data.util.toFormattedString
import com.example.a963103033239757ba10504dc3857ddc7.domain.model.SatelliteDetailModel
import com.example.a963103033239757ba10504dc3857ddc7.domain.repository.SatelliteListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(private val repository: SatelliteListRepository) :
    ViewModel() {

    val liveData = MutableLiveData<SatelliteDetailModel>()
    var positionLiveData = MutableLiveData<String>()

    private var index = 1 // start from the second index as the first one is already viewed.
    private var maxIndex = 0
    private val positionsList = ArrayList<PositionModel>()

    fun getSatelliteDetails(id: String) {
        val data = repository.getSatelliteDetails(id)
        data?.let {
            liveData.postValue(it)
            maxIndex = it.positionsList.lastIndex
            positionsList.clear()
            positionsList.addAll(it.positionsList)
        }
    }

    fun refreshSatellitePosition() {
        if (positionsList.isNotEmpty()) {
            positionLiveData.postValue(positionsList[index].toFormattedString())
            index = if (index == maxIndex) 0 else index + 1
        }
    }

}