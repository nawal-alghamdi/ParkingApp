package com.example.parkingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "parkingSpots")
data class ParkingInfo(
    @PrimaryKey val id: Int,
    val latitude: Double,
    val longitude: Double,
    @ColumnInfo(name = "parking_name") @SerializedName("parking_name") val parkingName: String,
    @ColumnInfo(name = "rate_per_hour") @SerializedName("rate_per_hour") val ratePerHour: Double,
    @ColumnInfo(name = "available_spots") @SerializedName("available_spots") val availableSpots: Int,
    @ColumnInfo(name = "total_spots") @SerializedName("total_spots") val totalSpots: Int,
    @ColumnInfo(name = "is_parking_time_over") val isParkingTimeOver: Boolean = false,
    @ColumnInfo(name = "is_active") val isActive: Boolean = false,
    @ColumnInfo(name = "reserved_time") val reservedTime: Long,
    @ColumnInfo(name = "end_time") val endTime: Long,
    @ColumnInfo(name = "actual_cost") val actualCost: Double,
)

