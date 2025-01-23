package com.example.parkingapp.data.source

import com.example.parkingapp.data.Result
import com.example.parkingapp.data.Result.Success
import com.example.parkingapp.data.source.database.ParkingDao
import com.example.parkingapp.data.source.remote.ParkingRemoteDataSource
import com.example.parkingapp.model.ParkingInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ParkingAppRepository @Inject constructor(
    private val remoteDataSource: ParkingRemoteDataSource,
    private val parkingDao: ParkingDao,
    private val ioDispatcher: CoroutineDispatcher
) : ParkingRepository {

    override suspend fun getParkingSpots(): Result<List<ParkingInfo>> {
        val result = remoteDataSource.getParkingSpots()
        return when {
            result is Success -> {
                result.data.forEach {
                    parkingDao.insertParkingSpot(it)
                }
                removeOldParkingSpots(result.data)
                result
            }

            else -> {
                val parkingList = getParkingSpotsFromDB()
                if (parkingList.isNotEmpty()) Success(parkingList) else result
            }
        }
    }

    override suspend fun addParkingSpot(parkingInfo: ParkingInfo) {
        parkingDao.insertParkingSpot(parkingInfo)
    }

    override suspend fun updateActiveParking(id: Int, isActive: Boolean) {
        parkingDao.updateActiveParking(id, isActive)
    }

    override suspend fun getActiveParkingSpots(): Result<List<ParkingInfo>> {
        return Success(parkingDao.getActiveParkingSpots())
    }

    override suspend fun markBookingAsCompleted(id: Int) {
        parkingDao.onBookingComplete(id)
    }

    override suspend fun getFinishedParkingSpots(): Result<List<ParkingInfo>> {
        return Success(parkingDao.getCompletedParkingSpots())
    }

    override suspend fun getParkingSpotsFromDB(): List<ParkingInfo> = withContext(ioDispatcher) {
        return@withContext parkingDao.getParkingSpots()
    }

    private suspend fun removeOldParkingSpots(newParkingSpots: List<ParkingInfo>) {
        val newParkingIds = newParkingSpots.map { parkingInfo -> parkingInfo.id }
        val allParkingSpots = parkingDao.getParkingSpots()
        allParkingSpots.filter { it.id !in newParkingIds && !it.isActive && !it.isParkingTimeOver }
            .let {
                parkingDao.delete(it)
            }
    }

}