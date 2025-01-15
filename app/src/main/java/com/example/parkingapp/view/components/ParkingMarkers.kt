package com.example.parkingapp.view.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.parkingapp.viewmodel.ParkingSpotsViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun ParkingMarkers(viewModel: ParkingSpotsViewModel) {
    val parkingSpots = viewModel.parkingSpots.collectAsState()
    parkingSpots.value.forEach { position ->
        Marker(
            state = MarkerState(
                position = LatLng(
                    position.latitude, position.longitude
                )
            ), title = position.parkingName,
            snippet = "Total spots: ${position.totalSpots}"
        )
    }
}