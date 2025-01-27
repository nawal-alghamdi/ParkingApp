package com.example.parkingapp.data.source

import com.example.parkingapp.data.Result
import com.example.parkingapp.model.ParkingInfo
import kotlinx.coroutines.flow.Flow

interface ParkingRepository {

    suspend fun getParkingSpots(): Result<List<ParkingInfo>>

    suspend fun addParkingSpot(parkingInfo: ParkingInfo)

    suspend fun updateActiveParking(id: Int, isActive: Boolean)

    fun getActiveParkingSpots(): Flow<List<ParkingInfo>>

    suspend fun markBookingAsCompleted(id: Int)

    fun getFinishedParkingSpots(): Flow<List<ParkingInfo>>

    suspend fun getParkingSpotsFromDB(): List<ParkingInfo>

}