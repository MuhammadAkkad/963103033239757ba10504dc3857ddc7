package com.example.a963103033239757ba10504dc3857ddc7.ui.fragment.satelliteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a963103033239757ba10504dc3857ddc7.domain.repository.SatelliteListRepository
import com.example.a963103033239757ba10504dc3857ddc7.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteListViewModel @Inject constructor(private val repository: SatelliteListRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    fun getList() {
        _uiState.value = UIState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            delay(600) // intentional delay to be able to see loading indicator.
            repository.getSatelliteList()
                .catch {
                    _uiState.value = UIState(error = it.message, isLoading = false)
                }
                .collect {
                    _uiState.value = UIState(data = it.data, isLoading = false)
                }
        }
    }
}

