package com.example.parkingapp.model

import com.google.gson.annotations.SerializedName

data class ParkingInfo(
    val id: Int,
    @SerializedName("parking_name")
    val parkingName: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("rate_per_hour")
    val ratePerHour: Int,
    @SerializedName("available_spots")
    val availableSpots: Int,
    @SerializedName("total_spots")
    val totalSpots: Int
)

