package com.example.gamecharacters.navigation

import androidx.navigation.NavGraphBuilder

interface NavigationFactory {
    fun create(builder: NavGraphBuilder, onAppBarState: (AppBarState) -> Unit)
}
