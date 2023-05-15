package com.panda.template.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import module.feature.plant.navigation.navigateToPlants
import module.feature.plant.navigation.plantsRoute
import module.feature.topic.navigation.navigateToTopic
import module.feature.topic.navigation.topicRoute
import modules.features.foryou.navigation.forYouNavigationRoute
import modules.features.foryou.navigation.navigateToForYou

@Composable
fun rememberComposeAppState(
    navHostController: NavHostController = rememberNavController()
): ComposeStuAppState{
    return remember(navHostController){
        ComposeStuAppState(navHostController)
    }
}

class ComposeStuAppState(
    val navController: NavHostController,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigateToDestination(destination: String) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (destination) {
            forYouNavigationRoute -> navController.navigateToForYou(navOptions)
            topicRoute -> navController.navigateToTopic(navOptions)
            plantsRoute -> navController.navigateToPlants(navOptions)
        }
    }


}