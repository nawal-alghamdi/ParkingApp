package com.example.parkingapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkingapp.model.ParkingInfo
import com.example.parkingapp.ui.theme.Green
import com.example.parkingapp.ui.theme.ParkingAppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ParkingItem(parkingSpot: ParkingInfo, showCompletedParking: Boolean) {
    val dateFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val startTime = dateFormatter.format(Date(parkingSpot.reservedTime))
    val endTime = dateFormatter.format(Date(parkingSpot.endTime))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = parkingSpot.parkingName,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$startTime to $endTime",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            val formattedCost = String.format(Locale.getDefault(), "%.2f", parkingSpot.actualCost)
            Text(
                text = "$${formattedCost}",
                modifier = Modifier.padding(end = 8.dp),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            // Show expired label if the parking is expired
            AnimatedVisibility(visible = showCompletedParking) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .background(Green)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Expired",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ParkingItemPreview() = ParkingAppTheme {
    val parkingSpot = ParkingInfo(
        1, 1.0, 2.0, "Parking 1",
        10.0, 5, 10, isParkingTimeOver = true,
        isActive = true, System.currentTimeMillis() - 3600000,
        System.currentTimeMillis() + 7200000, 18.5
    )

    ParkingItem(
        parkingSpot = parkingSpot,
        showCompletedParking = true
    )

}

