package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel
import com.example.a963103033239757ba10504dc3857ddc7.data.util.toPositionFormat
import com.example.a963103033239757ba10504dc3857ddc7.domain.repository.SatelliteListRepository
import com.example.a963103033239757ba10504dc3857ddc7.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject


@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(private val repository: SatelliteListRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    private val _uiStatePositions = MutableStateFlow(UIState())
    val uiStatePositions: StateFlow<UIState> = _uiStatePositions

    private var index = 0

    private var maxIndex = 0

    private val positionsList = ArrayList<PositionModel>()

    private var refreshJob: Job? = null

    private val delay = 3000L

    init {
        refreshJob = createJob()
    }

    fun getSatelliteDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.getSatelliteDetails(id)
                .catch {
                    _uiState.value = UIState(error = it.message)
                }
                .collect { resourcesStatus ->
                    resourcesStatus.data?.let { data ->
                        // on screen rotation continue from same index instead of starting from the beginning
                        data.lastPosition = data.positionsList[index].toPositionFormat()

                        // trigger observer with satellite data
                        _uiState.value = UIState(data = data)

                        // set max index range
                        maxIndex = data.positionsList.lastIndex

                        // to avoid consecutive additions
                        positionsList.clear()
                        positionsList.addAll(data.positionsList)

                        // after successfully fetching data start refreshing position
                        startRepeatingJob()
                    }

                }
        }
    }

    private fun startRepeatingJob() {
        refreshJob?.start()
    }

    private fun createJob(): Job {
        return viewModelScope.launch(Dispatchers.Main) {
            while (coroutineContext.isActive) {
                Log.i("refresher", index.toString())
                refreshSatellitePosition()
                delay(delay)
            }
        }
    }

    private fun refreshSatellitePosition() {
        if (positionsList.isNotEmpty()) {
            _uiStatePositions.value = UIState(data = positionsList[index].toPositionFormat())
            index = if (index == maxIndex) 0 else index + 1
        }
    }

}