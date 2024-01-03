package com.example.gamecharacters.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = navController.currentDestination?.route == "homeFragment",
            onClick = {
                navController.navigate("homeFragment") {
                    launchSingleTop = true
                    // Xóa hết ngăn xếp khi quay lại màn hình Home
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }
            }
        )

        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "View") },
            label = { Text("View") },
            selected = navController.currentDestination?.route == "viewFragment",
            onClick = {
                navController.navigate("viewFragment") {
                    launchSingleTop = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = navController.currentDestination?.route == "settingsFragment",
            onClick = {
                navController.navigate("settingsFragment") {
                    launchSingleTop = true
                }
            }
        )

        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = navController.currentDestination?.route == "profileFragment",
            onClick = {
                navController.navigate("profileFragment") {
                    launchSingleTop = true
                }
            }
        )
    }
}
