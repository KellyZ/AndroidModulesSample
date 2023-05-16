package module.feature.plant

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.map
import module.data.model.Plant

@Composable
fun PlantListScreen(
    onPlantClick: (Plant)->Unit,
    viewModel: PlantListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val plants by viewModel.plants.collectAsStateWithLifecycle()
//    val view = LocalView.current
    val gridState = rememberLazyGridState()
//    val context: Context = LocalContext.current.applicationContext
//    val jankStatsProxy = remember(view) {JankStatsProxy.getInstance(context)}
//    LaunchedEffect(gridState, gridState) {
//        snapshotFlow{ gridState.isScrollInProgress }.map { it }.collect { isScrolling ->
//            if (isScrolling) {
//                jankStatsProxy.putJankMetricState(view,"PlantList", "Scrolling")
//            } else {
//                jankStatsProxy.removeJankMetricState(view,"PlantList")
//            }
//        }
//    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 24.dp
        ),
        state = gridState,
        modifier = modifier.testTag("plants:list")
    ) {
        items(
            items = plants,
            key = { it.plantId }
        ) { plant->
            PlantListItem(plant = plant) {
                onPlantClick(plant)
            }
        }
    }
}


