package com.testapplication.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.testapplication.R
import com.testapplication.ui.screens.game.GameScreen
import com.testapplication.ui.screens.main.MainScreen
import com.testapplication.ui.screens.results.ResultScreens

@Composable
fun ScreenContent(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = BottomBarRoutes.MainScreen.name
) {
    Box {

        NavHost(
            modifier = modifier
                .fillMaxSize()
                .safeContentPadding(),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(BottomBarRoutes.MainScreen.name) {
                MainScreen {
                    navController.navigate(Routes.GameScreen.name + "/$it")
                }
            }

            composable(Routes.GameScreen.name + "/{${Arguments.DRAW_ID_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Arguments.DRAW_ID_ARGUMENT) {
                        type = NavType.IntType
                    }
                )) {
                GameScreen()
            }

            composable(BottomBarRoutes.WatchScreen.name) {
                WatchScreen()
            }

            composable(BottomBarRoutes.ResultScreens.name) {
                ResultScreens()
            }

        }

        val destination = navController.currentBackStackEntryAsState().value?.destination?.route
        if (destination != null && BottomBarRoutes.entries.map { it.name }.contains(destination)) {
            BottomBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                selectedScreen = destination
            ) {
                navController.navigate(it.name) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }

    }
}

enum class BottomBarRoutes(val iconId: Int) {
    MainScreen(R.drawable.main), ResultScreens(R.drawable.results), WatchScreen(R.drawable.play)
}

enum class Routes {
    GameScreen
}

object Arguments {
    const val DRAW_ID_ARGUMENT = "drawId"
}