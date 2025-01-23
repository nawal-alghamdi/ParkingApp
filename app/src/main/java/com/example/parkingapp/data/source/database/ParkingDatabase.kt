package com.example.parkingapp.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.parkingapp.model.ParkingInfo

@Database(entities = [ParkingInfo::class], version = 1)
abstract class ParkingDatabase : RoomDatabase() {
    abstract fun parkingDao(): ParkingDao
}