package com.example.gamecharacters.home.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.gamecharacters.home.presentation.characterList.composable.CharactersRoute
import com.example.gamecharacters.navigation.AppBarState
import com.example.gamecharacters.navigation.NavigationDirections
import com.example.gamecharacters.home.presentation.characterDetail.composable.CharacterDetailRoute
import com.example.gamecharacters.navigation.NavigationFactory
import javax.inject.Inject

class CharactersNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder, onAppBarState: (AppBarState) -> Unit) {
        builder.composable(NavigationDirections.CharacterList.destination) {
            CharactersRoute(onAppBarState)
        }

        builder.composable(
            route = NavigationDirections.CharacterDetailNavigation.ROUTE,
            arguments = NavigationDirections.CharacterDetailNavigation.argumentsList
        ) {
            CharacterDetailRoute(onAppBarState)
        }
    }
}
