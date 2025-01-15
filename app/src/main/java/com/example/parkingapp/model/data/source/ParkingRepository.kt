package com.example.parkingapp.model.data.source

import com.example.parkingapp.model.ParkingInfo
import com.example.parkingapp.model.data.Result

interface ParkingRepository {

    suspend fun getParkingSpots() : Result<List<ParkingInfo>>

}