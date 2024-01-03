package com.example.gamecharacters.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavigationHost(
    navController: NavHostController,
    factories: Set<NavigationFactory>,
    modifier: Modifier = Modifier,
    onAppBarState: (AppBarState) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDirections.CharacterList.destination,
        modifier = modifier
    ) {
        factories.forEach {
            it.create(this, onAppBarState)
        }
    }
}
