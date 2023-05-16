package module.feature.plant.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import module.data.model.Plant
import module.feature.plant.PlantListScreen

const val plantsRoute = "plants_route"

fun NavController.navigateToPlants(navOptions: NavOptions? = null) {
    this.navigate(plantsRoute, navOptions)
}

fun NavController.navigateToPlantDetail(plantId: String, navOptions: NavOptions? = null) {
    this.navigate("plantDetail/${plantId}", navOptions)
}

fun NavGraphBuilder.plantsScreen(onPlantClick:(Plant)->Unit) {
    composable(route = plantsRoute) {
        PlantListScreen(onPlantClick)
    }
}
