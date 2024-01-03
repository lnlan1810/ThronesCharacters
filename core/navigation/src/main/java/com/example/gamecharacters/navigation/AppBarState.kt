package com.example.gamecharacters.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class AppBarState(
    val title: String = "",
    val showNavigationIcon: Boolean = false,
    val actions: (@Composable RowScope.() -> Unit)? = null
)
