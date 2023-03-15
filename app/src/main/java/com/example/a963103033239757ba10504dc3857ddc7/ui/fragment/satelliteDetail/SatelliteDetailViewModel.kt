package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel
import com.example.a963103033239757ba10504dc3857ddc7.data.util.toFormattedString
import com.example.a963103033239757ba10504dc3857ddc7.domain.model.SatelliteDetailModel
import com.example.a963103033239757ba10504dc3857ddc7.domain.repository.SatelliteListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(private val repository: SatelliteListRepository) :
    ViewModel() {

    val satelliteLiveData = MutableLiveData<SatelliteDetailModel>()

    var positionLiveData = MutableLiveData<String>()

    private var index = 0

    private var maxIndex = 0

    private val positionsList = ArrayList<PositionModel>()

    private var refreshJob: Job? = null

    private val delay = 3000L

    fun getSatelliteDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getSatelliteDetails(id)
            data.let { flow ->
                flow.collect { model ->
                    run {
                        model?.let {
                            // on screen rotation continue from same index instead of starting from the beginning
                            model.lastPosition = model.positionsList[index].toFormattedString()

                            // trigger observer with satellite data
                            satelliteLiveData.postValue(model)

                            // set max index range
                            maxIndex = model.positionsList.lastIndex

                            // to avoid consecutive additions
                            positionsList.clear()
                            positionsList.addAll(model.positionsList)

                            // after successfully fetching data start refreshing position
                            refreshJob = startRepeatingJob()
                        }

                    }
                }
            }
        }
    }

    private fun refreshSatellitePosition() {
        Log.i("refresher", index.toString())
        if (positionsList.isNotEmpty()) {
            positionLiveData.postValue(positionsList[index].toFormattedString())
            index = if (index == maxIndex) 0 else index + 1
        }
    }

    private fun startRepeatingJob(): Job {
        return refreshJob
            ?: CoroutineScope(Dispatchers.Default).launch {
                while (coroutineContext.isActive) {
                    refreshSatellitePosition()
                    delay(delay)
                }
            }
    }

}