package com.example.parkingapp.data.source.remote

import com.example.parkingapp.model.ParkingInfo
import com.example.parkingapp.data.source.remote.api.ParkingApiService
import com.example.parkingapp.data.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ParkingRemoteDataSource @Inject constructor(
    private val apiService: ParkingApiService,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getParkingSpots(): Result<List<ParkingInfo>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(apiService.getParkingSpots())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}