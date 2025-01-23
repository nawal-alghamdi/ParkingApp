package com.example.parkingapp.data.source.remote.api

import com.example.parkingapp.model.ParkingInfo
import retrofit2.http.GET

interface ParkingApiService {

    companion object {
        const val ENDPOINT = "https://my.api.mockaroo.com/"
    }
    // I am using
    // schema "ParkingSpots"
    //generate 10, cache_for: 1.hour
    @GET("/parking_spots.json?key=6fbac760")
    suspend fun getParkingSpots(): List<ParkingInfo>

}