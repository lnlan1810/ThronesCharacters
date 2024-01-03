package com.example.gamecharacters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamecharacters.find.FindScreen
import com.example.gamecharacters.navigation.NavigationFactory
import com.example.gamecharacters.navigation.NavigationManager
import com.example.gamecharacters.navigation.createExternalRouter
import com.example.gamecharacters.profile.ProfileScreen
import com.example.gamecharacters.setting.SettingScreen
import com.example.gamecharacters.ui.ThronesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThronesTheme {
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
                            FindScreen(navController)
                        }
                        composable(Screen.Settings.route) {
                            SettingScreen(navController)
                        }
                        composable(Screen.Profile.route) {
                            ProfileScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
