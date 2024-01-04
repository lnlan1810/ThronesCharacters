package com.example.gamecharacters

sealed class Screen(val route: String) {
     object Home : Screen("homeFragment")
     object Settings : Screen("settingsFragment")
     object Find: Screen("viewFragment")
     object Profile: Screen("profileFragment",)
}
