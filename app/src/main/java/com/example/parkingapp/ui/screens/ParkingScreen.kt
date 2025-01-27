package com.example.parkingapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parkingapp.model.ParkingInfo
import com.example.parkingapp.ui.components.ParkingItem

@Composable
fun ParkingScreen(parkingSpots: State<List<ParkingInfo>>, showCompletedParking: Boolean) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(
            items = parkingSpots.value
        ) { index, parking ->
            AnimatedVisibility(visible = true) {
                ParkingItem(parking, showCompletedParking)
            }
        }
    }

}

