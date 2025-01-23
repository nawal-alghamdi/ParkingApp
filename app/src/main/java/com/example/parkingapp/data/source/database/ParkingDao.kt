package com.example.parkingapp.data.source.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parkingapp.model.ParkingInfo

@Dao
interface ParkingDao {
    @Query("SELECT * FROM parkingSpots")
    suspend fun getParkingSpots(): List<ParkingInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParkingSpot(parkingInfo: ParkingInfo)

    @Query("UPDATE parkingSpots SET is_active = :isActive WHERE id = :parkingId")
    suspend fun updateActiveParking(parkingId: Int, isActive: Boolean)

    @Query("SELECT * FROM parkingSpots WHERE is_active = 1")
    suspend fun getActiveParkingSpots(): List<ParkingInfo>

    @Query("UPDATE parkingSpots SET is_active = 0, is_parking_time_over = 1 WHERE id = :parkingId")
    suspend fun onBookingComplete(parkingId: Int)

    @Query("SELECT * FROM parkingSpots WHERE is_parking_time_over = 1")
    suspend fun getCompletedParkingSpots(): List<ParkingInfo>

    @Delete
    suspend fun delete(parkingSpots: List<ParkingInfo>)

}