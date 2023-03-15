package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satellite.SatelliteListModelItem
import com.example.a963103033239757ba10504dc3857ddc7.domain.repository.SatelliteListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteListViewModel @Inject constructor(private val repository: SatelliteListRepository) :
    ViewModel() {

    val liveData = MutableLiveData<ArrayList<SatelliteListModelItem>>()

    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getSatelliteList()
            data.collect() {
                run {
                    liveData.postValue(it as ArrayList<SatelliteListModelItem>)
                }
            }

        }
    }
}

