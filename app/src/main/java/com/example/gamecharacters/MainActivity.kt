package com.example.gamecharacters

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamecharacters.find.composable.FindScreen
import com.example.gamecharacters.navigation.BottomNavigationBar
import com.example.gamecharacters.navigation.HomeNavigation
import com.example.gamecharacters.navigation.NavigationFactory
import com.example.gamecharacters.navigation.NavigationManager
import com.example.gamecharacters.profile.ProfileScreen
import com.example.gamecharacters.setting.SettingsScreen
import com.example.gamecharacters.ui.ThronesTheme
import com.example.gamecharacters.ui.darkModeState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>

    @Inject
    lateinit var navigationManager: NavigationManager

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val darkModeState by darkModeState.collectAsStateWithLifecycle()

            ThronesTheme(darkTheme = darkModeState) {
                val navigationController = rememberNavController()
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController ,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Home.route) {
                            HomeNavigation(navigationController, navigationFactories, navigationManager)
                        }
                        composable(Screen.Find.route) {
                            FindScreen()
                        }
                        composable(Screen.Settings.route) {
                                SettingsScreen()
                        }
                        composable(Screen.Profile.route) {
                            ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}

