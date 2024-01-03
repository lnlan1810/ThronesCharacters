package com.example.gamecharacters.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object NavigationDirections {

    val CharacterList = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "characterListDestination"
    }

    object CharacterDetailNavigation {

        const val KEY_CHARACTER_ID = "characterId"
        const val ROUTE = "characterDetailDestination/{$KEY_CHARACTER_ID}"

        val argumentsList = listOf(
            navArgument(KEY_CHARACTER_ID) { type = NavType.StringType }
        )

        fun characterDetail(
            characterId: String
        ) = object : NavigationCommand {

            override val arguments = argumentsList

            override val destination = "characterDetailDestination/$characterId"
        }
    }
}
