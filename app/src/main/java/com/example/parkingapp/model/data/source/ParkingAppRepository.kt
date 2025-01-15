package com.example.parkingapp.model.data.source

import com.example.parkingapp.model.ParkingInfo
import com.example.parkingapp.model.data.Result
import com.example.parkingapp.model.data.source.remote.ParkingRemoteDataSource
import javax.inject.Inject

class ParkingAppRepository @Inject constructor(
    private val remoteDataSource: ParkingRemoteDataSource
) : ParkingRepository {

    override suspend fun getParkingSpots(): Result<List<ParkingInfo>> {
        return remoteDataSource.getParkingSpots()
    }

}