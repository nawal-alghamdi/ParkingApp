package com.example.parkingapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.parkingapp.ui.screens.HomeScreen
import com.example.parkingapp.ui.screens.ParkingScreen
import com.example.parkingapp.viewmodel.ParkingSpotsViewModel

@Composable
fun HomeRoute(viewModel: ParkingSpotsViewModel, modifier: Modifier = Modifier) {

    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Active", Icons.Default.Place),
        NavItem("Expired", Icons.Default.CheckCircle)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = "Icon")
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), viewModel, selectedIndex)
    }
}


@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    viewModel: ParkingSpotsViewModel,
    selectedIndex: Int
) {
    val showCompletedParking = selectedIndex == 2
    val parkingSpots = if (showCompletedParking) {
        viewModel.finishedParkingSpots.collectAsState(initial = emptyList())
    } else {
        viewModel.activeParkingSpots.collectAsState(initial = emptyList())
    }
    when (selectedIndex) {
        0 -> HomeScreen(viewModel)
        1 -> ParkingScreen(parkingSpots, false)
        2 -> ParkingScreen(parkingSpots, true)
    }
}