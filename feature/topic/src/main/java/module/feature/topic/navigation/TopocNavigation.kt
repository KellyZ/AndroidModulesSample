package module.feature.topic.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import module.feature.topic.TopicScreen

const val topicRoute = "topic_route"

fun NavController.navigateToTopic(navOptions: NavOptions? = null) {
    this.navigate(topicRoute, navOptions)
}

fun NavGraphBuilder.topicScreen(

){
    composable(route = topicRoute) {
        TopicScreen()
    }
}