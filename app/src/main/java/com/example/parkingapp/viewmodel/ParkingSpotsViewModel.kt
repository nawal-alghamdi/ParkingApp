package com.example.parkingapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingapp.model.ParkingInfo
import com.example.parkingapp.model.data.Result
import com.example.parkingapp.model.data.source.ParkingAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParkingSpotsViewModel @Inject constructor(
    private val parkingRepository: ParkingAppRepository
) : ViewModel() {

    private val _parkingSpots = MutableStateFlow(emptyList<ParkingInfo>())
    val parkingSpots: StateFlow<List<ParkingInfo>> = _parkingSpots.asStateFlow()

    init {
        viewModelScope.launch {
            val result = parkingRepository.getParkingSpots()
            if (result is Result.Success) {
                _parkingSpots.value = result.data
            }
        }
    }

}