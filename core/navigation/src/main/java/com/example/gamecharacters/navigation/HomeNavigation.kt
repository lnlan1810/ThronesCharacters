package com.example.gamecharacters.navigation

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun HomeNavigation(
    navController: NavHostController,
    navigationFactories: Set<NavigationFactory>,
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier
) {
    var appBarState by remember { mutableStateOf(AppBarState()) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = { MainTopAppBar(appBarState, navigateUp = { navController.navigateUp() }) }
    ) {
        NavigationHost(
            modifier = Modifier.padding(it),
            navController = navController,
            factories = navigationFactories
        ) { newAppBarState ->
            appBarState = newAppBarState
        }
    }

    DisposableEffect(navController) {
        val job = coroutineScope.launch {
            navigationManager
                .navigationEvent
                .collect { event ->
                    navController.navigate(event.destination, event.navOptions)
                }
        }
        onDispose {
            job.cancel()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainTopAppBar(appBarState: AppBarState, navigateUp: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = appBarState.title,
                fontWeight = FontWeight.Medium
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            if (appBarState.showNavigationIcon) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Back navigation"
                    )
                }
            }
        },
        actions = {
            appBarState.actions?.invoke(this)
        }
    )
}
