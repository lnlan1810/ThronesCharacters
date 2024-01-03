package com.example.gamecharacters

import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gamecharacters.extensions.collectWithLifecycle
import com.example.gamecharacters.navigation.AppBarState
import com.example.gamecharacters.navigation.NavigationFactory
import com.example.gamecharacters.navigation.NavigationHost
import com.example.gamecharacters.navigation.NavigationManager
import kotlinx.coroutines.launch

@Composable
fun HomeNavigation(
    navController: NavHostController,
    navigationFactories: Set<NavigationFactory>,
    navigationManager: NavigationManager
) {
    var appBarState by remember { mutableStateOf(AppBarState()) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
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
