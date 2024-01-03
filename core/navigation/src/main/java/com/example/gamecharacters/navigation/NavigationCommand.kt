package com.example.gamecharacters.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions

interface NavigationCommand {
    val destination: String

    val arguments: List<NamedNavArgument>

    val navOptions: NavOptions
        get() = NavOptions.Builder().build()
}
