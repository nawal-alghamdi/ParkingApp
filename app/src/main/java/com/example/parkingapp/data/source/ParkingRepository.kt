package com.example.parkingapp.data.source

import com.example.parkingapp.data.Result
import com.example.parkingapp.model.ParkingInfo

interface ParkingRepository {

    suspend fun getParkingSpots(): Result<List<ParkingInfo>>

    suspend fun addParkingSpot(parkingInfo: ParkingInfo)

    suspend fun updateActiveParking(id: Int, isActive: Boolean)

    suspend fun getActiveParkingSpots(): Result<List<ParkingInfo>>

    suspend fun markBookingAsCompleted(id: Int)

    suspend fun getFinishedParkingSpots(): Result<List<ParkingInfo>>

    suspend fun getParkingSpotsFromDB(): List<ParkingInfo>

}