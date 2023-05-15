package com.panda.template.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.panda.template.R
import module.feature.plant.PlantDetailActivity
import module.feature.plant.PlantDetailActivity.Companion.PLANTID_KEY
import module.feature.plant.navigation.plantsRoute
import module.feature.plant.navigation.plantsScreen
import module.feature.topic.navigation.topicRoute
import module.feature.topic.navigation.topicScreen
import modules.features.foryou.navigation.forYouNavigationRoute
import modules.features.foryou.navigation.forYouScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ComposeStuApp(
    appState: ComposeStuAppState = rememberComposeAppState()
) {
    val activity = (LocalContext.current as Activity)
    val onNavi:(String)->Unit = {
        appState.navigateToDestination(it)
    }
    Scaffold(
        bottomBar = {
            MyBottomNavigation(
                onNavigationToDesination = onNavi,
                currentDestination = appState.currentDestination
            )
        },
        // Enables for all composables in the hierarchy.
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        }
    ) { padding ->
        NavHost(
            navController = appState.navController,
            startDestination = forYouNavigationRoute,
            modifier = Modifier.padding(padding)
        ) {
            forYouScreen(onTopicClick = {})
            topicScreen()
            plantsScreen(
                onPlantClick = {
                    onPlantClick(activity, it.plantId)
                }
            )
        }
    }
}

private fun onPlantClick(activity: Activity, plantId: String):Unit {
    val intent = Intent()
    intent.putExtra(PLANTID_KEY, plantId)
    intent.setClass(activity, PlantDetailActivity::class.java)
    activity.startActivity(intent)
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(route: String) =
    this?.hierarchy?.any {
        it.route?.contains(route, true) ?: false
    } ?: false

@Composable
fun MyBottomNavigation(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onNavigationToDesination: (String) -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background
    ) {
        BottomNavigationItem(
            selected = currentDestination.isTopLevelDestinationInHierarchy(forYouNavigationRoute),
            onClick = {
                onNavigationToDesination(forYouNavigationRoute)

            },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_home))
            }
        )
        BottomNavigationItem(
            selected = currentDestination.isTopLevelDestinationInHierarchy(topicRoute),
            onClick = { onNavigationToDesination(topicRoute) },
            icon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_profile))
            }
        )
        BottomNavigationItem(
            selected = currentDestination.isTopLevelDestinationInHierarchy(plantsRoute),
            onClick = { onNavigationToDesination(plantsRoute) },
            icon = {
                Icon(imageVector = Icons.Default.Face, contentDescription = null)
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_plant))
            }
        )
    }
}